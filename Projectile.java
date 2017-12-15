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
      
      try{
        if(Math.abs(angle) <= 5){
          double objTLX = toCollide.getX();
          double objTLY = toCollide.getY();
          double objBLX = toCollide.getX();
          double objBLY = toCollide.getY() + toCollide.getHeight();
          
          double xDistTL = objTLX - (x + ((width) / 2));
          double yDistTL = objTLY - (y + ((height) / 2));
          double angleToTL = (double)Math.toDegrees(Math.atan2(yDistTL, xDistTL));
          double xDistBL = objBLX - (x + ((width) / 2));
          double yDistBL = objBLY - (y + ((height) / 2));
          double angleToBL = (double)Math.toDegrees(Math.atan2(yDistBL, xDistBL));
          
          System.out.println(angle);
          System.out.println(angleToTL);
          System.out.println(angleToBL);
          
          if((angle < angleToTL && angle > angleToBL) || (angle > angleToTL && angle < angleToBL)){
            return true;
          }
          else{
            return false;
          }
          
        } else if(Math.abs(Math.abs(angle) - 180) <= 5){
          double objTRX = toCollide.getX() + toCollide.getWidth();
          double objTRY = toCollide.getY();
          double objBRX = toCollide.getX() + toCollide.getWidth();
          double objBRY = toCollide.getY() + toCollide.getHeight();
          
          double xDistTR = objTRX - (x + ((width) / 2));
          double yDistTR = objTRY - (y + ((height) / 2));
          double angleToTR = (double)Math.toDegrees(Math.atan2(yDistTR, xDistTR));
          double xDistBR = objBRX - (x + ((width) / 2));
          double yDistBR = objBRY - (y + ((height) / 2));
          double angleToBR = (double)Math.toDegrees(Math.atan2(yDistBR, xDistBR));
          
          System.out.println(angle);
          System.out.println(angleToTR);
          System.out.println(angleToBR);
          
          if(angle < 0){
            angle = 360 + angle;
          }
          
          if(angleToTR < 0){
            angleToTR = 360 + angleToTR;
          }
          
          if(angleToTR < 0){
            angleToBR = 360 + angleToBR;
          }
          
          if((angle < angleToBR && angle > angleToTR) || (angle > angleToBR && angle < angleToTR)){
            return true;
          }
          else{
            return false;
          }
          
        } else if(Math.tan(Math.toRadians(angle)) > 0){
          double objTRX = toCollide.getX() + toCollide.getWidth();
          double objTRY = toCollide.getY();
          double objBLX = toCollide.getX();
          double objBLY = toCollide.getY() + toCollide.getHeight();
          
          double xDistTR = objTRX - (x + ((width) / 2));
          double yDistTR = objTRY - (y + ((height) / 2));
          double angleToTR = (double)Math.toDegrees(Math.atan2(yDistTR, xDistTR));
          double xDistBL = objBLX - (x + ((width) / 2));
          double yDistBL = objBLY - (y + ((height) / 2));
          double angleToBL = (double)Math.toDegrees(Math.atan2(yDistBL, xDistBL));
          
          System.out.println("TR, BL");
          System.out.println(Math.abs(angle));
          System.out.println(Math.abs(angleToTR));
          System.out.println(Math.abs(angleToBL));
          
          if((Math.abs(angle) > Math.abs(angleToTR) && Math.abs(angle) < Math.abs(angleToBL)) || (Math.abs(angle) < Math.abs(angleToTR) && Math.abs(angle) > Math.abs(angleToBL))){
            return true;
          }
          else{
            return false;
          }
        }
        else{
          double objTLX = toCollide.getX();
          double objTLY = toCollide.getY();
          double objBRX = toCollide.getX() + toCollide.getWidth();
          double objBRY = toCollide.getY() + toCollide.getHeight();
          
          double xDistTL = objTLX - (x + ((width) / 2));
          double yDistTL = objTLY - (y + ((height) / 2));
          double angleToTL = (double)Math.toDegrees(Math.atan2(yDistTL, xDistTL));
          double xDistBR = objBRX - (x + ((width) / 2));
          double yDistBR = objBRY - (y + ((height) / 2));
          double angleToBR = (double)Math.toDegrees(Math.atan2(yDistBR, xDistBR));
          
          System.out.println(Math.abs(angle));
          System.out.println(Math.abs(angleToTL));
          System.out.println(Math.abs(angleToBR));
          
          if((Math.abs(angle) > Math.abs(angleToTL) && Math.abs(angle) < Math.abs(angleToBR)) || (Math.abs(angle) < Math.abs(angleToTL) && Math.abs(angle) > Math.abs(angleToBR))){
            return true;
          }
          else{
            return false;
          }
        }
      }catch(Exception e){
        System.out.println("Something went wrong with sniper collision.");
        return false;
      }
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
}