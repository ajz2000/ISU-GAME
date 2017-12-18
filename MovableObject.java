import java.awt.*;
import java.util.*;

public abstract class MovableObject extends GraphicsObject{
  
  protected double velocity;
  protected double angle;
  //current frame of animation row
  protected int curFrame = 0;
  //how many frames in the animation
  //number of frames -1 (frames start at ZERO)
  protected int maxFrame = 0;
  //x position of frame top left in image
  protected int frameStart = 0;
  protected boolean movingUp = false;
  protected boolean movingDown = false;
  protected boolean movingLeft = false;
  protected boolean movingRight = false;
  protected boolean facingRight = false;
  
  public void move(){
    if(movingUp){
      if(movingRight){
        angle = 315;
      }
      else if(movingLeft){
        angle = 225;
      }
      else{
        angle = 270;
      }
    }
    else if(movingDown){
      if(movingRight){
        angle = 45;
      }
      else if(movingLeft){
        angle = 135;
      }
      else{
        angle = 90;
      }
    }
    else if(movingRight){
      angle = 0;
    }
    else if(movingLeft){ 
      angle = 180;
    }
    
    double Xa = (Math.cos(Math.toRadians(angle))*velocity);
    double Ya = (Math.sin(Math.toRadians(angle))*velocity);

    x += Xa;
    y += Ya;
    hitBox.x=(int)x;
    hitBox.y=(int)y;
    //reverts the player to standing position when they are not moving
    if(velocity==0){
    curFrame = 0;
    }
  }
  
  public void paint(Graphics2D g2d){
    //a quick implementation of left/right flipping of sprites
    if(movingRight){
      facingRight = true;
    }
    else if (movingLeft){
      facingRight = false;
    }
    if(facingRight){
      g2d.drawImage(sprite.getSubimage(curFrame*width, 0, width, height), (int)(x+width), (int)y, -width, height, null);
    }
    else{
      g2d.drawImage(sprite.getSubimage(curFrame*width, 0, width, height), (int)x, (int)y, width, height, null);
    }
    
    if(SSRB.getDebug()){
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
      g2d.setColor(Color.RED);
      g2d.fill(hitBox);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
  }
  
  public boolean collide(GraphicsObject toCollide){
    if(hitBox.intersects(toCollide.getHitBox())){
      return true;
    }
    else{
      return false;
    }
  }
  
  public double getAngle(){
  return angle;
  }
  
  public double getVelocity(){
  return velocity;
  }

}