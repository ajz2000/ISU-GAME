import java.awt.event.KeyEvent;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class PlayerCharacter extends Character{
  
  private int machineGunAmmo;
  private int sniperAmmo;
  private int shotgunAmmo;
  private int dodgeCooldown;
  private int accelTimer = 0;
  private String currentGun;
  
  PlayerCharacter(int x, int y){
    this.x = x;
    this.y = y;
    width = 16;
    height = 32; 
    try {
      sprite = ImageIO.read(new File("PlayerCharacterTemp.png"));
    } catch (IOException e) {
    } 
  }
  
  public void keyPressed(KeyEvent e){
     if (e.getKeyCode() == KeyEvent.VK_A){
      movingLeft = true;
     }
     else if (e.getKeyCode() == KeyEvent.VK_W){
      movingUp = true;
     }
     else if (e.getKeyCode() == KeyEvent.VK_D){
      movingRight = true;
     }
     else if (e.getKeyCode() == KeyEvent.VK_S){
      movingDown = true;
     }
     
  }
  
  public void keyReleased(KeyEvent e){
  if (e.getKeyCode() == KeyEvent.VK_A){
      movingLeft = false;
     }
     else if (e.getKeyCode() == KeyEvent.VK_W){
      movingUp = false;
     }
     else if (e.getKeyCode() == KeyEvent.VK_D){
      movingRight = false;
     }
     else if (e.getKeyCode() == KeyEvent.VK_S){
      movingDown = false;
     }
  }
  
  public void die(){
  }
  
  public void dodge(){
  }
  
  public void shoot(){
  }
  
  public void move(){
    if(movingUp||movingDown||movingLeft||movingRight){
      if(accelTimer >= 10){
        if(velocity < 5){
          velocity++;
        }
        accelTimer = 0;
      }
      else
      {
        accelTimer++;
      }
    }
    else{
      if(accelTimer >= 3){
        if(velocity > 0){
          velocity--;
        }
        accelTimer = 0;
      }
      else
      {
        accelTimer++;
      }
    }
    super.move();
  }
  
}