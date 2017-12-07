import java.awt.event.KeyEvent;
import java.awt.*;
public class PlayerCharacter extends Character{
  
  int machineGunAmmo;
  int sniperAmmo;
  int shotgunAmmo;
  int dodgeCooldown;
  String currentGun;
  
  PlayerCharacter(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public void keyPressed(KeyEvent e){
  }
  
  public void die(){
  }
  
  public void dodge(){
  }
  
  public void shoot(){
  }
  public void paint(Graphics2D g2d){
  g2d.setColor(Color.RED);
  g2d.fillRect(x,y,16,32);
  }
}