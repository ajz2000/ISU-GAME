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
    deathAnimFrames = 9;
    try {
      sprite = ImageIO.read(new File("EnemyExploding1.png"));
      deathSprite = ImageIO.read(new File("EnemyExplodingExplode.png"));
    } catch (IOException e) {
    } 
  }
  //move exploding enemies
  public void move(){
    if(!isExploding){
      super.move();
    }   
  if(isExploding){
    die();
  }
}

//explode
public void explode(){
  //resize hitbox to match the size of the explosion
  hitBox.x -= 20;
  hitBox.y -= 14;
  hitBox.width = 64;
  hitBox.height = 64;
  //checks collision of the explosion with the player, damages them if true
  if(collide(pc)){
    pc.setHealth(damage);
  }
}
//starts the enemies explosion
public void setExplode(){
  isExploding = true;
}
}