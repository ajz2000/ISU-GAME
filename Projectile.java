import java.awt.*;
import java.awt.geom.Line2D;
public class Projectile extends MovableObject{
  private int damage = 0;
  private String type = "";
  private boolean friendly = false;
  private int maxSniperDistance = 1000;
  private int sniperLife = 3;
  private float sniperComposite = 1.0f;
  private Line2D sniperShot;
  
  public Projectile(int x, int y, int size, double velocity, double angle, int damage, String type, boolean isFriendly){
    this.damage = damage;
    this.friendly = isFriendly;
    this.type = type;
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.angle = angle;
    width = size;
    height = size;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.height = size;
    hitBox.width = size;
    int drawXDistance = (int)(maxSniperDistance * Math.cos(Math.toRadians(angle)));
    int drawYDistance = (int)(maxSniperDistance * Math.sin(Math.toRadians(angle)));
    sniperShot = new Line2D.Float((int)x, (int)y, (int)(x + drawXDistance), (int)(y + drawYDistance));
  }
  
  public void paint(Graphics2D g2d){
    g2d.setColor(Color.WHITE);
    if (type.equals("Sniper")){
      int drawXDistance = (int)(maxSniperDistance * Math.cos(Math.toRadians(angle)));
      int drawYDistance = (int)(maxSniperDistance * Math.sin(Math.toRadians(angle)));
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, sniperComposite);
      g2d.setComposite(alphaComposite);
      g2d.drawLine((int)x, (int)y, (int)(x + drawXDistance), (int)(y + drawYDistance));
      
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    else{
      g2d.fillRect((int)x, (int)y, width, height);
    }
  }
  
  public void move(){
    if(!type.equals("Sniper")){
      super.move();
    }
    else
    {
      if(sniperLife > 0){
        sniperLife--;
      }
      else
      {
        if(sniperComposite > 0.01){
          sniperComposite -= 0.01f;
        }
        else{
          this.isActive = false;
        }
      }
    }
  }
  
  public boolean collide(GraphicsObject toCollide){
    if(type.equals("Sniper")){
      if(sniperComposite < 1.0f){
        return false;
      }
      if(sniperShot.intersects(toCollide.getHitBox())){
        return true;
      }
      return false;
    }
    else{
      return super.collide(toCollide);
    }
  }
  
  public int getDamage(){
    return damage;
  }
  
  public String getType(){
    return type;
  }
  
  public boolean getFriendly(){
    return friendly;
  }
}