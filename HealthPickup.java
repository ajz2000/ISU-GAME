import javax.imageio.*;
import java.io.*;

public class HealthPickup extends Pickup{
  public HealthPickup(int x, int y){
    this.x = x;
    this.y = y;
    hitBox.y = y;
    hitBox.x = x;
    this.width = 8;
    this.height = 18;
    hitBox.width = 8;
    hitBox.height = 18;
    value = 10;
    try {
      sprite = ImageIO.read(new File("HealthPickup.png"));
    } catch (IOException e) {
    } 
  }
}