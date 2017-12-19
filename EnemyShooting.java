import javax.imageio.*;
import java.io.*;

public class EnemyShooting extends Enemy{
  
  private boolean shooting = false;
  private int shootTimer = 0;
  private int shootDelay = 0;
  
 public EnemyShooting(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 58;
    height = 48; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 1;
    health = 60;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    damage = 20;
    try {
      sprite = ImageIO.read(new File("EnemyShooting1.png"));
    } catch (IOException e) {
    } 
 }
 //if robot is not shooting, move.
 public void move(){
   if(shooting){
     shoot();
   } else{
     super.move();
   }
 }
 
 //if the distance to the player is within a specified range, begin shooting
 public boolean collide(GraphicsObject toCollide){   
   if(toCollide instanceof PlayerCharacter){
     if(distanceToPlayer < 160){
       shooting = true;
       return true;
     } else{
       return false;
     }
   } else{
     return super.collide(toCollide);
   }
 }
 
 //shoot at the player if within range
 private void shoot(){
   if(shootTimer < 100){
     shootTimer++;
   } else{
     shooting = false;
     Projectile toAdd;
     
     double xDist = (pc.getX() + (pc.getWidth() / 2)) - (x + ((width) / 2));
     double yDist = (pc.getY() + (pc.getHeight() / 2)) - (y + ((height) / 2));
     
     double bulletAngle = (double)Math.toDegrees(Math.atan2(yDist, xDist));
     
     toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 4, 5.0, bulletAngle, damage, "Pistol", false);
     
     ssrb.addBullet(toAdd);
     
     shootTimer = 0;
     shooting = false;
     if(Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)))>160){
   super.move();
   }
   }
   
 }
}