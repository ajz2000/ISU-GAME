import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;

public class SSRB extends JPanel{
  
  private static int scaleRatio = 4;
  private Color backGroundGreen = new Color(59,206,113);  
  //stores screenwidth/height
  private int screenWidth;
  private int screenHeight;
  //the player character
  private PlayerCharacter pc;
  private RobotCompanion rc;
  private HUD hud;
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
      }
    });
    setFocusable(true); 
    //get the screen height/width
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenWidth = (int)screenSize.getWidth();
    screenHeight = (int)screenSize.getHeight();
    //create a new playercharacter in the middle of the screen
    pc = new PlayerCharacter(screenWidth/2,screenHeight/2);
    rc = new RobotCompanion(pc);
    hud = new HUD(pc);
  }
  
  @Override
  public void paint(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
    
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //draw the background
    g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,screenWidth,screenHeight);
    
    //draw the player
    pc.paint(g2d);
    rc.paint(g2d);
    hud.paint(g2d);
  }
  
  public void move(){
    pc.move();
    rc.move();
  }
  
  public static int getScaleRatio(){
  return scaleRatio;
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