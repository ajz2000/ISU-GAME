public class Enemy extends Character{
  protected int damage;
  protected PlayerCharacter pc;
  protected SSRB ssrb;
  protected int floatTimer = 0;
  protected boolean floatingUp = false; 
  protected double distanceToPlayer;
  protected int maxVelocity;
  
  public void move(){
    if(angle > 90 && angle < 270){
    facingRight = false;
    }
    else{
    facingRight = true;
    }
    
    distanceToPlayer = Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)));
      
      angle = (double) Math.toDegrees(Math.atan2(pc.getY() - y, pc.getX() - x));
      
      if(angle < 0){
        angle += 360;
      }
      if(distanceToPlayer<8){
        velocity = 0;
      }
      else{
      velocity = maxVelocity;
      }
      
    if(floatTimer>=40){
      floatTimer = 0;
      if(floatingUp){
        y = y + 1;
        floatingUp = false;
      }
      else if(!floatingUp){
        y = y - 1;
        floatingUp = true;
      }
    }
    else{
    floatTimer++;
    }
    super.move();
  }
}