import javax.imageio.*;
import java.io.*;

public class EnemyShooting extends Enemy{
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
    try {
      sprite = ImageIO.read(new File("EnemyShooting1.png"));
    } catch (IOException e) {
    } 
 }
}