import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;

public class SSRB extends JPanel{
  
  private Color backGroundGreen = new Color(59,206,113);  
  //stores screenwidth/height
  private int screenWidth;
  private int screenHeight;
  //the player character
  private PlayerCharacter pc;
  
    SSRB(){
    //get the screen height/width
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenWidth = (int)screenSize.getWidth();
    screenHeight = (int)screenSize.getHeight();
    //create a new plaercharacter in the middle of the screen
    pc = new PlayerCharacter(screenWidth/2,screenHeight/2);
  }
    
  @Override
  public void paint(Graphics g){
     Graphics2D g2d = (Graphics2D) g;
     //draw the background
     g2d.setColor(backGroundGreen);
    g2d.fillRect(0,0,screenWidth,screenHeight);
    
    //draw the player
    pc.paint(g2d);
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
      Thread.sleep(10);
    }
  }
  
}