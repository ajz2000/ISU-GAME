import javax.imageio.*;
import java.io.*;

public class EnemyExploding extends Enemy{

  private boolean isExploding;
  private int explodeTimer = 0;

  public EnemyExploding(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 24;
    height = 32; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 3;
    health = 30;
    damage = 40;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    try {
      sprite = ImageIO.read(new File("EnemyExploding1.png"));
    } catch (IOException e) {
    } 
 }
  //move exploding enemies
  public void move(){
    if(!isExploding){
      super.move();
    } else{
      if(explodeTimer < 200){
        explodeTimer++;
      } else{
        explode();
        isExploding = false;
      }
    }
  }
  //explode
  public void explode(){
    //resize hitbox to match the size of the explosion
    hitBox.x -= 32;
    hitBox.y -= 32;
    hitBox.width = 64;
    hitBox.height = 64;
    //checks collision of the explosion with the player, damages them if true
    if(collide(pc)){
      pc.setHealth(damage);
    }
    //kills the robot
    die();
  }
 //starts the enemies explosion
  public void setExplode(){
    isExploding = true;
  }
}