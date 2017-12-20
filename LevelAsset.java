import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelAsset extends GraphicsObject{
  
  private BufferedImage bgLayer;
  private BufferedImage fgLayer;
  private char[][] levelCollision;
  
  public LevelAsset(String levelName){
    try {
      bgLayer = ImageIO.read(new File(levelName + "Back.png"));
      fgLayer = ImageIO.read(new File(levelName + "Front.png"));
    } catch (IOException e) {
    } 
  }
  public void paint(Graphics2D g2d){
    g2d.drawImage(bgLayer,(int)-SSRB.getXOffset(),(int)-SSRB.getYOffset(),null);
    paintFG(g2d);
  }
  private void paintFG(Graphics2D g2d){
    g2d.drawImage(fgLayer,(int)-SSRB.getXOffset(),(int)-SSRB.getYOffset(),null);
  }
}

