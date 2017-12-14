import java.awt.*;

public class Projectile extends MovableObject{
  private int damage = 0;
  private String type = "";
  private boolean friendly = false;
  private int maxSniperDistance = 1000;
  private int sniperLife = 3;
  private float sniperComposite = 1.0f;

  public Projectile(int x, int y, int size, double velocity, double angle, int damage, String type, boolean isFriendly){
    this.damage = damage;
    this.friendly = friendly;
    this.type = type;
    this.x = x;
    this.y = y;
    this.velocity = velocity;
    this.angle = angle;
    width = size;
    height = size;
  }
  
  public void paint(Graphics2D g2d){
    g2d.setColor(Color.WHITE);
    if (type.equals("Sniper")){
      double raa = angle % 90;
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
      try{
        if(Math.tan(Math.toRadians(angle)) > 0){
          System.out.println("Reached");
          double objTLX = toCollide.getX();
          double objTLY = toCollide.getY();
          double objBRX = toCollide.getX() + toCollide.getWidth();
          double objBRY = toCollide.getY() + toCollide.getHeight();
          double xDistTL = objTLX - (x + ((width) / 2));
          double yDistTL = objTLY - (y + ((height) / 2));
          double angleToTL = (double)Math.toDegrees(Math.atan2(yDistTL, xDistTL));
          double xDistBR = objTLX - (x + ((width) / 2));
          double yDistBR = objTLY - (y + ((height) / 2));
          double angleToBR = (double)Math.toDegrees(Math.atan2(yDistBR, xDistBR));
          
          if((Math.abs(angle) > Math.abs(angleToTL) && Math.abs(angle) < Math.abs(angleToBR)) || (Math.abs(angle) < Math.abs(angleToTL) && Math.abs(angle) > Math.abs(angleToBR))){
            return true;
          }
          else{
            return false;
          }
        }
        else{
          
        }
      }catch(Exception e){
        return false;
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
}