import javax.imageio.*;
import java.io.*;

public class EnemyBasic extends Enemy{
 public EnemyBasic(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 32;
    height = 32; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 1;
    try {
      sprite = ImageIO.read(new File("EnemyBasic1.png"));
    } catch (IOException e) {
    } 
 }
}