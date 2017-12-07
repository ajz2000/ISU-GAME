import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsObject{
  
  int x;
  int y;
  int width;
  int height;
  BufferedImage sprite;
  boolean isActive;
  
  public void paint(Graphics2D g2d){
  }
  
}
