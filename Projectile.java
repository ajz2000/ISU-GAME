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
  //draw bullet
  public void paint(Graphics2D g2d){
    if(friendly){
      g2d.setColor(new Color(117, 74, 131));
    }else{
      g2d.setColor(Color.RED);
    }
    //if sniper, draw a line
    if (type.equals("Sniper")){
      int drawXDistance = (int)(maxSniperDistance * Math.cos(Math.toRadians(angle)));
      int drawYDistance = (int)(maxSniperDistance * Math.sin(Math.toRadians(angle)));
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, sniperComposite);
      g2d.setComposite(alphaComposite);
      g2d.drawLine((int)x, (int)y, (int)(x + drawXDistance), (int)(y + drawYDistance));      
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    else{
      //if a bullet, draw bullet
      g2d.fillRect((int)x, (int)y, width, height);
    }
  }
  
  public void move(){
    //standard movement for all non-sniper bullets
    if(!type.equals("Sniper")){
      super.move();
    }
    else
    {
      //fade sniper shot and then deactivate
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
  //check collision with another entity
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
  //return damage ammount
  public int getDamage(){
    return damage;
  }
  //return shot type
  public String getType(){
    return type;
  }
  //return true if bullet is firendly (player's bullets)
  public boolean getFriendly(){
    return friendly;
  }
}