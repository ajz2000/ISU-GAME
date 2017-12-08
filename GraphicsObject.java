import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsObject{
  
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected BufferedImage sprite;
  protected boolean isActive;
  
  public void paint(Graphics2D g2d){
    g2d.drawImage(sprite, x, y, width, height, null);
  }
  
}
