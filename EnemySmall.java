import javax.imageio.*;
import java.io.*;

public class EnemySmall extends Enemy{
  
  public EnemySmall(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 16;
    height = 16; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 4;
    health = 10;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    damage = 1;
    try {
      sprite = ImageIO.read(new File("EnemySmall1.png"));
    } catch (IOException e) {
    } 
  }
  
}