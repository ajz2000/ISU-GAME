import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;

public class SSRB extends JPanel{
  
  private static int scaleRatio = 2;
  private Color backGroundGreen = new Color(59,206,113);  
  //stores screenwidth/height
  private static int screenWidth;
  private static int screenHeight;
  //the player character
  private PlayerCharacter pc;
  //the robot companion
  private RobotCompanion rc;
  //the hud
  private HUD hud;
  //the list of active projectiles
  private ArrayList<Projectile> bulletList = new ArrayList<Projectile>();
  //list of all active enemies
  private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
  //list of walls
  private ArrayList<Wall> wallList= new ArrayList<Wall>();
  //list of pickUps
  private ArrayList<Pickup> pickupList = new ArrayList<Pickup>();
  //Toggle for debug (Currently includes: Hitboxes.)
  private static boolean debug = false;
  //current level
  private LevelAsset currentLevel = new LevelAsset("Level1",this);
  //offset
  private static double xOffset;
  private static double yOffset;
  //enemy director
  private Director director;
  //Audio Director
  private AudioDirector audioDirector;
  
  public SSRB(){
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyReleased(KeyEvent e) {
        pc.keyReleased(e);
      }
      @Override
      public void keyPressed(KeyEvent e) {
        pc.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_Z){
          SSRB.debug = !SSRB.debug;
        }
      }
    });
    
    addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }
      @Override
      public void mouseEntered(MouseEvent e) {
      }
      @Override
      public void mouseExited(MouseEvent e) {
      }
      public void mousePressed(MouseEvent e) {
        rc.mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        rc.mouseReleased(e);
      }
    });
    
    setFocusable(true); 
    setDoubleBuffered(true);
    //get the screen height/width
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenWidth = (int)screenSize.getWidth();
    screenHeight = (int)screenSize.getHeight();
    //create a new playercharacter, with a robot companion in the middle of the screen
    pc = new PlayerCharacter(screenWidth/2/SSRB.getScaleRatio(),screenHeight/2/SSRB.getScaleRatio(),this);
    rc = new RobotCompanion(pc, this);
//    creates 3 basic enemies for testing purposes
//    enemyList.add(new EnemyBasic(40,40,pc,this));
//    enemyList.add(new EnemyExploding(80,80,pc,this));
//    enemyList.add(new EnemyShooting(200,80,pc,this));
//    initialise the hud
    hud = new HUD(pc, rc);
    director = new Director(this,pc);
    audioDirector = new AudioDirector(this);
    
    audioDirector.start();
  }
  
  @Override
  public void paint(Graphics g){
    boolean FGDrawn = false;
    
    Graphics2D g2d = (Graphics2D) g;
    super.paint(g);
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //Set Scale Ratio
    g2d.scale(scaleRatio, scaleRatio);
    //translate the "camera"
    g2d.translate(-xOffset+(screenWidth/4), - yOffset+(screenHeight/4));
    //draw the background
    g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,screenWidth,screenHeight);
    
    currentLevel.paintBG(g2d); 
    
    for(int i = 0; i < wallList.size(); i++){
      if(wallList.get(i).getHitBox().intersects(pc.getHeadHitBox())){
        currentLevel.paintFG(g2d);
        FGDrawn = true;
        break;
      }
    }
    
    //draw the player
    pc.paint(g2d);
    
    
    
    if(!FGDrawn){
      currentLevel.paintFG(g2d);
    }
    rc.paint(g2d);
    //loop through every bullet in bulletList
    for(int i = 0; i < bulletList.size(); i++){
      bulletList.get(i).paint(g2d);
    }
    //loop through every enemy in enemyList
    for(int i = 0; i < enemyList.size(); i++){
      enemyList.get(i).paint(g2d);
    }
    //loop through every wall in wallList
    for(int i = 0; i < wallList.size(); i++){
      wallList.get(i).paint(g2d);
    }
    //loop through every pickup in pickupList
    for (int i = 0; i < pickupList.size(); i++){
      pickupList.get(i).paint(g2d);
    }
    hud.paint(g2d, this);
  }
  
  public void move(){
    //move the player and robot
    pc.move();
    rc.move();
    
    //move all active enemies
    for(int i = 0; i < enemyList.size(); i++){
      enemyList.get(i).move();
    }
    
    //Check for enemies to delete
    for(int i = 0; i < enemyList.size(); i++){
      if(!enemyList.get(i).isActive){
        enemyList.remove(i);
      }
    }
    
    //Check for bullets to delete
    for(int i = 0; i < bulletList.size(); i++){
      if(!bulletList.get(i).isActive){
        bulletList.remove(i);
      }
    }
    //check for pickups to delete
    for(int i = 0; i < pickupList.size(); i++){
      if(!pickupList.get(i).isActive){
        pickupList.remove(i);
      }
    }
    //move every active bullet
    for(int i = 0; i < bulletList.size(); i++){
      bulletList.get(i).move();
    }
    //check all collisions
    checkCollisions();
    
    if(enemyList.size() == 0){
      director.calculateEnemies();
    }
    
    audioDirector.setVolume();
  }
  
  public void checkCollisions(){
    //bullet and enemy collision
    for(int i = 0; i < bulletList.size(); i++){
      for(int j = 0; j < enemyList.size(); j++){
        if(bulletList.get(i).collide(enemyList.get(j)) && bulletList.get(i).getFriendly()){
          enemyList.get(j).setHealth(bulletList.get(i).getDamage());
          
          if(!bulletList.get(i).getType().equals("Sniper")){
            bulletList.get(i).setActive(false);
          }
        }
      }
    }
    
    //bullet and player collision
    for(int i = 0; i < bulletList.size(); i++){
      if(bulletList.get(i).collide(pc) && !bulletList.get(i).getFriendly()&&!pc.getDodging()){
        pc.setHealth(bulletList.get(i).getDamage());
        
        bulletList.get(i).setActive(false);
      }
    }
    
    //enemy and player collision
    for(int i = 0; i < enemyList.size(); i++){
      if(enemyList.get(i).collide(pc)&&!pc.getDodging()){
        if(enemyList.get(i) instanceof EnemyBasic||enemyList.get(i) instanceof EnemySmall){
          pc.setHealth(enemyList.get(i).getDamage());
        } else if(enemyList.get(i) instanceof EnemyExploding){
          EnemyExploding toExplode = (EnemyExploding)enemyList.get(i);
          toExplode.setExplode();
        }
      }
    }
    //player and wall
    for(int i = 0; i <wallList.size(); i++){
      pc.footCollide(wallList.get(i));
    }
    
    //player and pickup
    for(int i = 0; i < pickupList.size(); i++){
      if(pc.collide(pickupList.get(i))){
        if(pickupList.get(i) instanceof HealthPickup && pc.getHealth()<100){
          pc.setHealth(-1*pickupList.get(i).getValue());
          pickupList.get(i).collect();
        }
        else if(pickupList.get(i) instanceof AmmoPickup){
          rc.addAmmo(pickupList.get(i).getType(),pickupList.get(i).getValue());   
          pickupList.get(i).collect();
        }
      }
    }
  }
  
  //add a bullet to the list of active projectiles
  public void addBullet(Projectile p){
    bulletList.add(p);
  }
  //add a wall to the list of active walls
  public void addWall(Wall w){
    wallList.add(w);
  }
  //add a pickup to the list of active pickups
  public void addPickup(Pickup p){
    pickupList.add(p);
  }
  //add an enemy to the list of active enemies
  public void addEnemy(Enemy e){
    enemyList.add(e);
  }
  //returns the scaling factor of the window
  public static int getScaleRatio(){
    return scaleRatio;
  }
  //return X position of the window
  public int getXPosition(){
    return this.getX();
  }
  //return Y position of the window
  public int getYPosition(){
    return this.getY();
  }
  //returns true if debug is active
  public static boolean getDebug(){
    return debug;
  }
  
  public static double getXOffset(){
    return xOffset;
  }
  public static double getYOffset(){
    return yOffset;
  }
  public static void setXOffset(double xOffset){
    SSRB.xOffset = xOffset;
  }
  public static void setYOffset(double yOffset){
    SSRB.yOffset = yOffset;
  }
  public static int getScreenHeight(){
    return screenHeight;
  }
  public static int getScreenWidth(){
    return screenWidth;
  }
  public RobotCompanion getRobotCompanion(){
    return rc;
  }
  public PlayerCharacter getPlayer(){
    return pc;
  }
  public ArrayList<Enemy> getEnemyList(){
    return enemyList;
  }
  public static void main(String[] args) throws InterruptedException, IOException {
    
    JFrame frame = new JFrame("SUPER SPICY ROBOT BOYS 23");
    SSRB s = new SSRB();
    frame.add(s);
    
    //set the window size to match the screen size
    frame.setSize(s.screenWidth,s.screenHeight);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    while (true){
      s.repaint();
      s.move();
      Thread.sleep(10);
    }
  }
}