import java.awt.*;

public class Projectile extends MovableObject{
  private int damage = 0;
  private String type = "";
  private boolean friendly = false;
  private int maxSniperDistance = 400;
  private int sniperLife = 3;

  public Projectile(int x, int y, double velocity, double angle, int damage, String type, boolean isFriendly){
    this.damage = damage;
    this.friendly = friendly;
    this.type = type;
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.angle = angle;
    width = 4;
    height = 4;
  }
  
  public void paint(Graphics2D g2d){
    if (type.equals("Sniper")){
      double raa = angle % 90;
      int drawXDistance = (int)(maxSniperDistance * Math.cos(Math.toRadians(angle)));
      int drawYDistance = (int)(maxSniperDistance * Math.sin(Math.toRadians(angle)));
      g2d.drawLine((int)x, (int)y, (int)(x + drawXDistance), (int)(y + drawYDistance));
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
        this.isActive = false;
      }
    }
  }
}