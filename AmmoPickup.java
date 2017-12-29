import javax.imageio.*;
import java.io.*;

public class AmmoPickup extends Pickup{
  //gun types: 0 - Pistol, 1 - Shotgun, 2 - Sniper, 3 - Machine Gun
  
  public AmmoPickup(int x, int y, int type){
    
    this.x = x;
    this.y = y;
    hitBox.y = y;
    hitBox.x = x;
    this.width = 24;
    this.height = 22;
    hitBox.width = 24;
    hitBox.height = 2;
    this.type = type;
    
    try {
      if(type == 1){
        value = 4;
        sprite = ImageIO.read(new File("AmmoShotgun.png"));
      }
      else if (type == 2){
        value = 2;
        sprite = ImageIO.read(new File("AmmoSniper.png"));
      }
      else if (type == 3){
        value = 20;
        sprite = ImageIO.read(new File("AmmoMachineGun.png"));
      }
    } catch (IOException e) {
      //in case something breaks
      value = 1;
      System.out.println("something broke");
      
    }
    
  }

}