import java.awt.*;

public class Projectile extends MovableObject{
  private int damage = 0;
  private String type = "";
  private boolean friendly = false;
  
  public Projectile(int x, int y, double velocity, double angle, int damage, String type, boolean isFriendly){
    this.damage = damage;
    this.friendly = friendly;
    this.type = type;
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.angle = angle;
    width = 2;
    height = 2;
  }
  
  public void paint(Graphics2D g2d){
    g2d.fillRect((int)x, (int)y, width, height);
  }
}