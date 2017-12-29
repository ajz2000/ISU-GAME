import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GraphicsObject{
  
  protected double x = 0;
  protected double y = 0;
  protected int width;
  protected int height;
  protected BufferedImage sprite;
  protected boolean isActive = true;
  protected Rectangle hitBox = new Rectangle();
  
  //draws the sprite at a specific position on screen
  public void paint(Graphics2D g2d){
    g2d.drawImage(sprite, (int)x, (int)y, width, height, null);
  }
  //returns X position
  public double getX(){
    return x;
  }
  //returns Y position
  public double getY(){
    return y;
  }
  //returns width
  public int getWidth(){
    return width;
  }
  //returns height
  public int getHeight(){
    return height;
  }
  //set if the object is active or not
  public void setActive(boolean active){
    isActive = active;
  }
  //returns the hitbox for checking against other hitboxes
  public Rectangle getHitBox(){
    return hitBox;
  }
  
}
