import javax.imageio.*;
import java.io.*;

public class EnemySpawning extends Enemy{
  
  private boolean spawning = false;
  private int spawnTimer = 0;
  private int spawnDelay = 0;
  
 public EnemySpawning(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 22;
    height = 26; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 1;
    health = 60;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    damage = 20;
    deathAnimFrames = 4;
    try {
      sprite = ImageIO.read(new File("EnemySpawning1.png"));
      deathSprite = ImageIO.read(new File("EnemyBasicDeath.png"));
    } catch (IOException e) {
    } 
 }
 //if robot is not shooting, move.
 public void move(){
   if(spawning){
     spawn();
   } else{
     super.move();
   }
   if(health <= 0){
     die();
   }
 }
 
 //if the distance to the player is within a specified range, begin shooting
 public boolean collide(GraphicsObject toCollide){   
   if(toCollide instanceof PlayerCharacter){
     if(distanceToPlayer < 160){
       spawning = true;
       return true;
     } else{
       return false;
     }
   } else{
     return super.collide(toCollide);
   }
 }
 
 //shoot at the player if within range
 private void spawn(){
   if(spawnTimer < 100){
     spawnTimer++;
   } else{
     spawning = false;

     ssrb.addEnemy(new EnemySmall((int)x,(int)y,pc,ssrb));
     
     spawnTimer = 0;
     spawning = false;
     if(Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)))>160){
   super.move();
   }
   }
   
 }
}