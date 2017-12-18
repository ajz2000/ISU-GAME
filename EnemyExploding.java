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
    maxVelocity = 2;
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
  
  public void explode(){
    hitBox.x -= 32;
    hitBox.y -= 32;
    hitBox.width = 64;
    hitBox.height = 64;
    
    if(collide(pc)){
      pc.setHealth(damage);
    }
    
    die();
  }
 
  public void setExplode(){
    isExploding = true;
  }
}