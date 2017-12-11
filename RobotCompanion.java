import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.event.*; 

public class RobotCompanion extends Character{
  private PlayerCharacter pc;
  private int accelTimer = 0;
  private int floatTimer = 0;
  private double distanceToPlayer;
  private boolean floatingUp = false;
  private SSRB ssrb;
  private double mouseX;
  private double mouseY;
  
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
  }
  
  public void shoot(MouseEvent e){
    //Get mouse x and y
    mouseX = e.getX() / SSRB.getScaleRatio();
    mouseY = e.getY() / SSRB.getScaleRatio();
    
    //Find x and y distance between mouse and companion's centre.
    double xDist = mouseX - (x + ((width) / 2));
    double yDist = mouseY - (y + ((height) / 2));
    
    //Find angle to fire by using Tan-1(opp/adj or y/x)
    double bulletAngle = (double)Math.toDegrees(Math.atan2(yDist, xDist));
    
    Projectile toAdd = new Projectile((int)(x + (width / 2)), (int)(y + (height / 2)), 5.0, bulletAngle, 10, "Pistol", true);
    
    ssrb.addBullet(toAdd);
  }
}
