import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class RobotCompanion extends Character{
  private PlayerCharacter pc;
  private int accelTimer = 0;
  private int floatTimer = 0;
  private double distanceToPlayer;
  private boolean floatingUp = false;
  public RobotCompanion(PlayerCharacter pc){
    this.pc=pc;
    width = 16;
    height = 16; 
    x = pc.getX() - 16 * SSRB.getScaleRatio();
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
    
    if(distanceToPlayer>=24*SSRB.getScaleRatio()){
      if(velocity<5&&accelTimer>15){
        velocity++;
        accelTimer = 0;
      }
    }
    else {
      if(velocity>0&&accelTimer>5){
        velocity--;
        accelTimer = 0;
      }
    }
    
    if(floatTimer>=40){
      floatTimer = 0;
      if(floatingUp){
        y = y + 1*SSRB.getScaleRatio();
        floatingUp = false;
      }
      else if(!floatingUp){
        y = y - 1*SSRB.getScaleRatio();
        floatingUp = true;
      }
    }
    int Xa = (int)(Math.cos(Math.toRadians(angle))*velocity);
    int Ya = (int)(Math.sin(Math.toRadians(angle))*velocity);
    x += Xa;
    y +=Ya;
  }
}
