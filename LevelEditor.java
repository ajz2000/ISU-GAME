//MOVE CURSOR WITH WASD
//LOAD WITH L
//NEW LEVEL WITH N
//UNLOAD WITH U
//SAVE WITH ENTER
//CHANGE TYPE OF TILE WITH THE NUMBER KEYS
//MORE FUNCTIONALITY COMING SOON

//testtesttest

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

public class LevelEditor extends JPanel implements Runnable{
  private Color backGroundGreen = new Color(59,206,113);  
  private Selector selector;
  private String fileName = "";
  private EditorInput input;
  private BufferedImage background1 = null;
  //this says "wall one"
  private BufferedImage wall1 = null;
  //N = Neutral, B = Bottom, T = Top(edge), L = Left, R = Right
  private BufferedImage wallTopN = null;
  private BufferedImage wallTopBR = null;
  private BufferedImage wallTopL = null;
  private BufferedImage wallTopTL = null;
  private BufferedImage wallTopTR = null;
  private BufferedImage character1 = null;
  //DUDE ENCAPSULATION LMAO
  private int tileSize = 16;
  private boolean loaded = false;
  private char[][] levelArray = null;
  private boolean mouseHeld = false;
  private MouseEvent clicked;
  private boolean mouseMode = false;
  public static int width = 0;
  public static int height = 0;
  
  public LevelEditor(){
    
    addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
      }
      @Override
      public void keyReleased(KeyEvent e) {
      }
      @Override
      public void keyPressed(KeyEvent e) {
        selector.keyPressed(e);
        if(e.getKeyCode() == KeyEvent.VK_N&&loaded==false){
          createLevel();
        }
        if(e.getKeyCode() == KeyEvent.VK_L&&loaded==false){
          loadLevel();
          loaded = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_P)
          printArray();
        if(e.getKeyCode() == KeyEvent.VK_U)
          unload();
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
          save();
        if(e.getKeyCode() == KeyEvent.VK_Z){
          if(mouseMode){
            mouseMode = false;
          }
          else{
            mouseMode = true;
          }
        }
        if(e.getKeyCode() == KeyEvent.VK_F){
          selector.fill(levelArray);
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
        clicked = e;
        mouseHeld = true;
      }
      public void mouseReleased(MouseEvent e) {
        mouseHeld = false;
      }
    });
    
    setFocusable(true); 
    try {
      background1 = ImageIO.read(new File("background1.png"));
      wall1 = ImageIO.read(new File("wall1.png"));
      wallTopN = ImageIO.read(new File("cielingMiddle.png"));
      wallTopBR = ImageIO.read(new File("cielingBottomRight.png"));
      wallTopL = ImageIO.read(new File("cielingLeft.png"));
      wallTopTL = ImageIO.read(new File("cielingTopLeft.png"));
      wallTopTR = ImageIO.read(new File("cielingTopRight.png"));
      character1 = ImageIO.read(new File("character1.png"));
    } catch (IOException e) {
      System.out.println("Could not load files");
    }
    
    selector = new Selector(this);
  } 
  
  @Override
  public void paint(Graphics g)  {
    updateFrame();
    
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,1920,1080);
    g2d.drawImage(background1,0,0,null);
    drawArray(g2d);
    selector.draw(g2d);
  }
  
  public void updateFrame(){
    if(mouseHeld && mouseMode){
      selector.mousePressed(clicked);
    }
  }
  
  public void run(){
    while (true){
      repaint();
      
      try{
        Thread.sleep(10);
      }catch(InterruptedException e){
        System.out.println("Couldn't sleep level editor thread.");
      }
    }
  }
  
  public void createLevel(){
    System.out.println("Enter a file name, then level width, then level height");
    try{
      input = new EditorInput(this, true);
    }
    catch (Exception e){
      fileName = "";
    }
  }
  
  public void finishCreate(){
    fileName = input.getName();
    height = input.getHeight();
    width = input.getWidth();
    
    String workingDir = System.getProperty("user.dir");
    File f = new File(workingDir + "/CustomLevels/" + fileName + ".txt");
    
    try{
      
      f.createNewFile();
      FileWriter fw = new FileWriter(f);
      fw.close();
    } catch (Exception e){
      e.printStackTrace();
    }
    levelArray = new char[height][width];
    
    //Fill the array with values to make sure it loads width properly
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        levelArray[i][j] = 'o';
      }
    }
    loaded = true;
  }

  public void loadLevel(){
    //get the file you're going to load
    
    System.out.println("file to load??");
    
    try{
      input = new EditorInput(this, false);
    } 
    catch (Exception e){
      fileName = "";
    }
  }
  
  public void finishLoad(){
    fileName = input.getLoad();
    
    String workingDir = System.getProperty("user.dir");
    File f = new File(workingDir + "/CustomLevels/" + fileName + ".txt");
    
    //load the file
    try{
      
      FileReader fr = new FileReader(f);
      BufferedReader br2 = new BufferedReader(fr);
      String line = "";
      int tempHeight = 0;
      //gets the height and width (i hope o lord do i hope)
      while ((line=br2.readLine()) != null){
        //im just assuming that all the lines are the same length LMAO ITS ELEVEN O'CLOCK I REALLY HOPE THIS WORKS
        width = line.length(); 
        tempHeight++;
      }
      height = tempHeight;
      br2.close();
    } catch (Exception e){
      System.out.println("LEVEL PROBABLY CANT BE LOADED L M A O");
    }
    levelArray = new char[height][width];
    //sigh.........
    try{
      
      FileReader fr2 = new FileReader(f);
      BufferedReader br3 = new BufferedReader(fr2);
      int currentLine = 0;
      //o lord another one im so tired i cant even think must continue at all costs
      String line ="";
      while ((line=br3.readLine()) != null){
        for(int i = 0; i<= width-1; i++){
          
          levelArray[currentLine][i] = line.charAt(0);
          
          line = line.substring(1);
          
        }
        currentLine++;
      }
      br3.close();
    } catch(Exception e){
      System.out.println("dear god i dont even remember what im doing im just on autopilot here hope it works");
    }
  }
  
  public void printArray(){
    for(int i = 0; i <= height-1; i++){
      for(int j = 0; j <= width-1; j++){
        System.out.print(levelArray[i][j]);
      }
      System.out.println();
    }
  }
  
  public void drawArray(Graphics2D g2d){
    for(int i = 0; i <= height-1; i++){
      for(int j = 0; j <= width-1; j++){
        if(levelArray[i][j] == 'c'){
          g2d.drawImage(character1,j*tileSize, i*tileSize-tileSize,null);
        }
        else if(levelArray[i][j] == 'x'){
          g2d.drawImage(wall1,j*tileSize, i*tileSize,null);
        }
        else if(levelArray[i][j] == 't'){
          //First check top of array, then top of wall, then left of array, then left of wall, then bottom right of array, then bottom right of wall, default to neutral.
          if(i == (0))
          {
            if(j == 0)
            {
              g2d.drawImage(wallTopTL,j*tileSize, i*tileSize,null);
            }
            else if(j == (width - 1))
            {
              g2d.drawImage(wallTopTR,j*tileSize, i*tileSize,null);
            }
            else if(levelArray[i][j - 1] != 't')
            {
              g2d.drawImage(wallTopTL,j*tileSize, i*tileSize,null);
            }
            else if(levelArray[i][j + 1] != 't')
            {
              g2d.drawImage(wallTopTR,j*tileSize, i*tileSize,null);
            }
            else
            {
              g2d.drawImage(wallTopN,j*tileSize, i*tileSize,null);
            }
          }
          else if(levelArray[i - 1][j] != 't')
          {
            if(j == 0)
            {
              g2d.drawImage(wallTopTL,j*tileSize, i*tileSize,null);
            }
            else if(j == (width - 1))
            {
              g2d.drawImage(wallTopTR,j*tileSize, i*tileSize,null);
            }
            else if(levelArray[i][j - 1] != 't')
            {
              g2d.drawImage(wallTopTL,j*tileSize, i*tileSize,null);
            }
            else if(levelArray[i][j + 1] != 't')
            {
              g2d.drawImage(wallTopTR,j*tileSize, i*tileSize,null);
            }
            else
            {
              g2d.drawImage(wallTopN,j*tileSize, i*tileSize,null);
            }
          }
          else if(j == 0)
          {
            g2d.drawImage(wallTopL,j*tileSize, i*tileSize,null);
          }
          else if(levelArray[i][j - 1] != 't')
          {
            g2d.drawImage(wallTopL,j*tileSize, i*tileSize,null);
          }
          else if(j == (width - 1))
          {
            if(i == (height - 1))
            {
              g2d.drawImage(wallTopBR,j*tileSize, i*tileSize,null);
            }
            else
            {
              g2d.drawImage(wallTopN,j*tileSize, i*tileSize,null);
            }
          }
          else if(levelArray[i][j + 1] != 't' && levelArray[i + 1][j] != 't')
          {
            g2d.drawImage(wallTopBR,j*tileSize, i*tileSize,null);
          }
          else
          {
            g2d.drawImage(wallTopN,j*tileSize, i*tileSize,null);
          }
        } 
      }
    }
  }
  public void unload(){
    width = 0;
    height = 0;
    levelArray = null;
    loaded = false;
    fileName = "";
  }
  public void save(){
    System.out.println("Saving level");
    
    String workingDir = System.getProperty("user.dir");
    File f = new File(workingDir + "/CustomLevels/" + fileName + ".txt");
    
    try{
      FileWriter fw = new FileWriter(f);
      PrintWriter pw = new PrintWriter(fw);
      String currentLine = "";
      for(int i = 0; i <= height-1; i++){
        for(int j = 0; j <= width-1; j++){
          currentLine = currentLine + levelArray[i][j];
        }
        pw.println(currentLine);
        currentLine = "";
      }
      pw.close();
    } catch(IOException e){
      System.out.println("Save Failed");
    }
  }
  
  public int getTileSize(){
    return tileSize;
  }
  
  public boolean getLoaded(){
    return loaded;
  }
  
  public char getLevelArray(int y, int x){
    return levelArray[y][x];
  }
  
  public void setLevelArray(int y, int x, char c){
    levelArray[y][x] = c;
  }
  
  public boolean getMouseHeld(){
    return mouseHeld;
  }
  
  public boolean getMouseMode(){
    return mouseMode;
  }
  
//  public int getWidth(){
//    return width;
//  }
//  
//  public int getHeight(){
//    return height;
//  }
}