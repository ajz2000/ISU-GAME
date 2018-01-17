import javax.imageio.*;
import java.io.*;

public class EnemyBasic extends Enemy{
  
  public EnemyBasic(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 20;
    height = 28; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 2;
    health = 20;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    deathAnimFrames = 4;
    damage = 1;
    try {
      sprite = ImageIO.read(new File("EnemyBasic1.png"));
       deathSprite = ImageIO.read(new File("EnemyBasicDeath.png"));
    } catch (IOException e) {
    } 
  }
  
}