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
  private int screenWidth;
  private int screenHeight;
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
  //Toggle for debug (Currently includes: Hitboxes.)
  private static boolean debug = false;
  
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
    //get the screen height/width
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenWidth = (int)screenSize.getWidth();
    screenHeight = (int)screenSize.getHeight();
    //create a new playercharacter, with a robot companion in the middle of the screen
    pc = new PlayerCharacter(screenWidth/2/SSRB.getScaleRatio(),screenHeight/2/SSRB.getScaleRatio());
    rc = new RobotCompanion(pc, this);
    //creates 3 basic enemies for testing purposes
    enemyList.add(new EnemyBasic(40,40,pc,this));
    enemyList.add(new EnemyExploding(80,80,pc,this));
    enemyList.add(new EnemyShooting(200,80,pc,this));
    //initialise the hud
    hud = new HUD(pc, rc);
  }
  
  @Override
  public void paint(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //Set Scale Ratio
    g2d.scale(scaleRatio, scaleRatio);
    
    //draw the background
    g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,screenWidth,screenHeight);
    
    //draw the player
    pc.paint(g2d);
    rc.paint(g2d);
    hud.paint(g2d);
    
    //loop through every bullet in bulletList
    for(int i = 0; i < bulletList.size(); i++){
      // paint the bullet at location i in the array.
      bulletList.get(i).paint(g2d);
    }
    //loop through every bullet in bulletList
    for(int i = 0; i < enemyList.size(); i++){
      // paint the enemy at location i in the array.
      enemyList.get(i).paint(g2d);
    }
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
    
    //move every active bullet
    for(int i = 0; i < bulletList.size(); i++){
      bulletList.get(i).move();
    }
    //check all collisions
    checkCollisions();
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
        if(enemyList.get(i) instanceof EnemyBasic){
          pc.setHealth(enemyList.get(i).getDamage());
        } else if(enemyList.get(i) instanceof EnemyExploding){
          EnemyExploding toExplode = (EnemyExploding)enemyList.get(i);
          toExplode.setExplode();
        }
      }
    }
  }
  //add a bullet to the list of active projectiles
  public void addBullet(Projectile p){
    bulletList.add(p);
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