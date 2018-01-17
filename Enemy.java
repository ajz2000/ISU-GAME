public class Enemy extends Character{
  protected int damage;
  protected PlayerCharacter pc;
  protected SSRB ssrb;
  protected int floatTimer = 0;
  protected boolean floatingUp = false; 
  protected double distanceToPlayer;
  protected int maxVelocity;
  protected boolean moving = true;
  protected boolean collidingWithWall;
  protected boolean hasAdjusted = false;
  protected boolean hasExploded = false;
  //moves the enemy in the direction of the player
  public void move(){
    if(health>0){
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
    if(health <= 0){
      die();
    }
  }
  
//return enenmy's damage
  public int getDamage(){
    return damage;
  }
  
  public void die(){
    
    int deathAnimFrameLength = 15;
    sprite = deathSprite;
    
    if(this instanceof EnemyBasic){
      if(!hasAdjusted){
        x -=15;
        y-=15;
        hasAdjusted = true;
      }
      width = height = 50;
      deathAnimFrameLength = 5;
    }
    else if(this instanceof EnemyExploding){
      if(!hasAdjusted){
        x -=21;
        y-=14;
        hasAdjusted = true;
      }
      width = height = 64;
      deathAnimFrameLength = 7;
      if(curFrame == 6 && !hasExploded){
        EnemyExploding toExplode = (EnemyExploding)this;
        toExplode.explode();
        hasExploded = true;
      }
      
    }
    
    deathAnimTimer++;
    if(deathAnimTimer>=deathAnimFrameLength){
      if(curFrame<deathAnimFrames){
        curFrame++;
      }
      deathAnimTimer=0;
      if(curFrame==deathAnimFrames){
        super.die();
        double drop = Math.random()*100;
        
        if(drop <= 10){
          ssrb.addPickup(new HealthPickup((int)x,(int)y));
        }
        else if (drop > 10 && drop <= 20){
          ssrb.addPickup(new AmmoPickup((int)x,(int)y,1));
        }
        else if(drop > 20 && drop <= 30){
          ssrb.addPickup(new AmmoPickup((int)x,(int)y,2));
        }
        else if(drop > 30 && drop <= 40 ){
          ssrb.addPickup(new AmmoPickup((int)x,(int)y,3));
        }
      }
    }
    
    
  }
}