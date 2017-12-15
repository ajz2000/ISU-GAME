import javax.imageio.*;
import java.io.*;

public class EnemyExploding extends Enemy{

  private boolean isExploding;

  public EnemyExploding(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 24;
    height = 32; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 2;
    health = 30;
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
   
    distanceToPlayer = Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)));
    
    if(distanceToPlayer<32){
      explode();
    }
    else{
    super.move();
    }
 }
 public void explode(){
 }
}