import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsObject{
  
  protected double x = 0;
  protected double y = 0;
  protected int width;
  protected int height;
  protected BufferedImage sprite;
  protected boolean isActive = true;
  
  public void paint(Graphics2D g2d){
    g2d.drawImage(sprite, (int)x, (int)y, width, height, null);
  }
  
  public double getX(){
    return x;
  }
  
  public double getY(){
    return y;
  }
  
  public int getWidth(){
    return width;
  }
  
  public int getHeight(){
    return height;
  }
  
  public void setActive(boolean active){
    isActive = active;
  }
}
