import java.awt.*;

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
    
    int Xa = (int)(Math.cos(Math.toRadians(angle))*velocity);
    int Ya = (int)(Math.sin(Math.toRadians(angle))*velocity);
    x += Xa;
    y +=Ya;
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
    g2d.drawImage(sprite.getSubimage(curFrame*width, 0, width, height), x+width*SSRB.getScaleRatio(), y, -width*SSRB.getScaleRatio(), height*SSRB.getScaleRatio(), null);
    }
    else{
    g2d.drawImage(sprite.getSubimage(curFrame*width, 0, width, height), x, y, width*SSRB.getScaleRatio(), height*SSRB.getScaleRatio(), null);
    }
  }
  public double getAngle(){
  return angle;
  }
  public double getVelocity(){
  return velocity;
  }
}