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
  
  //moves the object a set distance (velocity) in the direction specified by angle
  public void move(){

    //calculate the vertical and horizontal acceration;
    double Xa = (Math.cos(Math.toRadians(angle))*velocity);
    double Ya = (Math.sin(Math.toRadians(angle))*velocity);
    //adjust the object's x/y basede on the horizontal acceleration
    x += Xa;
    y += Ya;

    //update the hitbox's coordinates to match those of the player
    hitBox.x=(int)x;
    hitBox.y=(int)y;
    
  }
  
  public void paint(Graphics2D g2d){
    //left/right flipping of sprites
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
    //draws hitbox if in debug mode
    if(SSRB.getDebug()){
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
      g2d.setColor(Color.RED);
      g2d.fill(hitBox);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
  }
  //checks collision
  public boolean collide(GraphicsObject toCollide){
    if(hitBox.intersects(toCollide.getHitBox())){
      return true;
    }
    else{
      return false;
    }
  }
  //returns object's angle
  public double getAngle(){
  return angle;
  }
  //returns object's velocity
  public double getVelocity(){
  return velocity;
  }

}