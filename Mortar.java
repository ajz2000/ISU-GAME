import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Mortar{
  private int radius;
  private double centreX;
  private double centreY;
  private boolean active = true;
  private Ellipse2D.Double circle;
  
  public Mortar(double centreX, double centreY){
    this.centreX = centreX;
    this.centreY = centreY;
    radius = 5;
  }
  
  public void update(){
    radius++;
    
    circle = new Ellipse2D.Double(centreX - radius, centreY - radius, radius * 2, radius * 2);
  }
  
  public void paint(Graphics2D g2d){
    g2d.setColor(Color.CYAN);
    
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    g2d.fillOval((int)(centreX - radius), (int)(centreY - radius), radius * 2, radius * 2);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
  }
  
  public boolean collide(PlayerCharacter pc){
    if(radius > 40){
      active = false;
      return circle.intersects(pc.getHitBox().x, pc.getHitBox().y, pc.getHitBox().width, pc.getHitBox().height);
    } else {
      return false;
    }
  }
  
  public boolean getActive(){
    return active;
  }
}