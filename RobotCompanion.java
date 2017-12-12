import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*; 

public class RobotCompanion extends Character{
  private PlayerCharacter pc;
  private int machineGunAmmo = 60;
  private int sniperAmmo = 5;
  private int shotgunAmmo = 10;
  private int accelTimer = 0;
  private int floatTimer = 0;
  private int shotgunTimer = 0;
  private int sniperTimer = 0;
  private int machinegunTimer = 0;
  private double distanceToPlayer;
  private boolean floatingUp = false;
  private SSRB ssrb;
  private double mouseX;
  private double mouseY;
  private boolean machineGunShooting = false;
  //Integer stores gun types: 0 - Pistol, 1 - Shotgun, 2 - Sniper, 3 - Machine Gun
  private int currentGun = 0;
  
  public RobotCompanion(PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 16;
    height = 16; 
    x = pc.getX() - 16;
    y = pc.getY();
    try {
      sprite = ImageIO.read(new File("RobotCompanion1.png"));
    } catch (IOException e) {
    } 
  }
  
  public void move(){
    
    distanceToPlayer = Math.sqrt(((pc.getX()-x)*(pc.getX()-x)) + ((pc.getY()-y)*(pc.getY()-y)));
    angle = (double) Math.toDegrees(Math.atan2(pc.getY() - y, pc.getX() - x));
    
    if(angle < 0){
      angle += 360;
    }
    
    accelTimer++;
    floatTimer++;
    
    if(distanceToPlayer>=24){
      if(velocity<2&&accelTimer>15){
        velocity++;
        accelTimer = 0;
      }
    }
    else {
      if(velocity>0&&accelTimer>2){
        velocity--;
        accelTimer = 0;
      }
    }
    if(floatTimer>=40){
      floatTimer = 0;
      if(floatingUp){
        y = y + 1;
        floatingUp = false;
      }
      else if(!floatingUp){
        y = y - 1;
        floatingUp = true;
      }
    }
    int Xa = (int)(Math.cos(Math.toRadians(angle))*velocity);
    int Ya = (int)(Math.sin(Math.toRadians(angle))*velocity);
    x += Xa;
    y +=Ya;
    
    if(machineGunShooting){
      shootMachineGun(mouseX,mouseY);
    }
     if (shotgunTimer < 60){
        shotgunTimer++;
      }
     if (sniperTimer < 100){
        sniperTimer++;
      }
     if (machinegunTimer < 10){
        machinegunTimer++;
      }
  }
  
  public void shoot(MouseEvent e){
    Projectile toAdd;
    //Get mouse x and y
    mouseX = e.getX() / SSRB.getScaleRatio();
    mouseY = e.getY() / SSRB.getScaleRatio();
    
    //Find x and y distance between mouse and companion's centre.
    double xDist = mouseX - (x + ((width) / 2));
    double yDist = mouseY - (y + ((height) / 2));
    
    //Find angle to fire by using Tan-1(opp/adj or y/x)
    double bulletAngle = (double)Math.toDegrees(Math.atan2(yDist, xDist));
    switch(currentGun){
      case 0:
        toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 5.0, bulletAngle, 10, "Pistol", true);
        ssrb.addBullet(toAdd);
        break;
      case 1:
        if(shotgunAmmo>0&&shotgunTimer>=60){
        for (int i = 0; i <=5; i++){
          toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 5.0, bulletAngle+((Math.random()*30)-15), 10, "Shotgun", true);
          ssrb.addBullet(toAdd);
        }
        shotgunTimer = 0;
        shotgunAmmo--;
      }
        
        break;
      case 2:
        if(sniperAmmo>0&&sniperTimer>=100){
        toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 10.0, bulletAngle, 10, "Sniper", true);
        ssrb.addBullet(toAdd);
        sniperAmmo--;
        sniperTimer = 0;
      }
    }
  }
  public void shootMachineGun(double mouseX, double mouseY){
    if (machineGunAmmo>0&&machinegunTimer>=10){
      Projectile toAdd;
      double xDist = mouseX - (x + ((width) / 2));
      double yDist = mouseY - (y + ((height) / 2));
      double bulletAngle = (double)Math.toDegrees(Math.atan2(yDist, xDist));
      toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 7.0, bulletAngle, 10, "MachineGun", true);
      ssrb.addBullet(toAdd);
      machineGunAmmo--;
      machinegunTimer = 0;
    }
  }
  public void mousePressed(MouseEvent e){
    if(e.getButton() == MouseEvent.BUTTON1){
      if(currentGun == 3){   
        
        machineGunShooting = true;
      }
      shoot(e);
    }
    else if(e.getButton() == MouseEvent.BUTTON3){
      if(currentGun < 3){
        currentGun++;
      }
      else{
        currentGun = 0;
      }
    }
  }
  
  public void mouseReleased(MouseEvent e){
    if (e.getButton() == MouseEvent.BUTTON1){
      machineGunShooting = false;   
    }
  }
  
  public int getCurrentGun(){
    return currentGun;
  }
  
  public int getAmmo(int gun){
    switch(gun){
      case 1:
        return shotgunAmmo;
      case 2:
        return sniperAmmo;
      case 3:
        return machineGunAmmo;
      default:
        return 0;
    }
  }
}
