public class Enemy extends Character{
  protected int damage;
  protected PlayerCharacter pc;
  protected SSRB ssrb;
  protected int floatTimer = 0;
  protected boolean floatingUp = false; 
  protected double distanceToPlayer;
  protected int maxVelocity;
  protected boolean moving = true;
  
  //moves the enemy in the direction of the player
  public void move(){
    //sets facingRight true/false
    if(angle > 90 && angle < 270){
    facingRight = false;
    }
    else{
    facingRight = true;
    }
    //calculate distance to player usi
    distanceToPlayer = Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)));
      
    //calculate angle to the player
      angle = (double) Math.toDegrees(Math.atan2(pc.getY() - y, pc.getX() - x));
      //adjusts the angle to be positive
      if(angle < 0){
        angle += 360;
      }
      //stops enemies if they are within a certain distance of the palyer
      //stops them from "freaking out" and attempting to reach the position they are already at
      if(distanceToPlayer<8){
        velocity = 0;
      }
      else{
        //if the enemy is not in range of the player, accelerate
      velocity = maxVelocity;
      }
     // makes robots float up and down
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
    //call super's move
    super.move();
  }
  //return enenmy's damage
  public int getDamage(){
    return damage;
  }
}