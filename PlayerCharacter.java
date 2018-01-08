import java.awt.event.KeyEvent;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class PlayerCharacter extends Character{
  
  private int accelTimer = 0;
  private int runAnimTimer = 0;
  private int dodgeTimer = 0;
  private int dodgeDelayTimer = 0;
  private int hitInvinciblityTimer = 0;
  private boolean dodging = false;
  private Rectangle footHitBox = new Rectangle();
  private Rectangle headHitBox = new Rectangle();
  private double prevX;
  private double prevY;
  
  PlayerCharacter(int x, int y){
    this.x = x;
    this.y = y;
    width = 32;
    height = 64; 
    health = 100;
    maxFrame = 3;
    hitBox.x=x;
    hitBox.x=y;
    hitBox.width = width;
    hitBox.height = height;
    footHitBox.height = 2;
    footHitBox.width = width;
    headHitBox.height = 33;
    headHitBox.width = width;
    try {
      sprite = ImageIO.read(new File("PlayerCharacter1.png"));
    } catch (IOException e) {
    } 
  }
  //sets direction based on WASD keypress
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
    else if (e.getKeyCode() == KeyEvent.VK_SPACE){
      if(dodgeDelayTimer >= 50){
        dodgeDelayTimer = 0;
        dodge();
      }
    }
  }
  //un-sets direction based on WASD key release
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
  
  //begins the player's dodge
  public void dodge(){
    dodging = true;
    velocity = 8;
  }
  
  public void move(){
    prevX = x;
    prevY = y;
    
    if(isActive){
    //accelerate and decelerate the player
    if(movingUp||movingDown||movingLeft||movingRight){
      if(accelTimer >= 10){
        if(velocity < 4){
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
      if(accelTimer >= 3&&!dodging){
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
    
    //increments the current frame whenever player is moving (running animation)
    runAnimTimer++;
    if(runAnimTimer>=15){
      curFrame++;
      runAnimTimer=0;
      if(curFrame>maxFrame){
        curFrame = 0;
      }
    }
    
    //Increases dodge timer and resets velocity and timer when timer is done.
    if(dodging){
      if(dodgeTimer >= 30){
        velocity = 4;
        dodgeTimer = 0;
        dodging = false;
        if (!movingUp&&!movingDown&&!movingLeft&&!movingRight){
          velocity = 0;
        }
      }
      else{
        dodgeTimer++;
      }
    } else if(dodgeDelayTimer < 50){
        dodgeDelayTimer++;
    }
    
    //I-Frame counter.
    if(hitInvinciblityTimer < 100){
      hitInvinciblityTimer++;
    }

    //calls the movable objects default movement (
    //super.move();
        //player's angle calculation
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
    
    //calculate the vertical and horizontal acceration;
    double Xa = (Math.cos(Math.toRadians(angle))*velocity);
    double Ya = (Math.sin(Math.toRadians(angle))*velocity);
    //adjust the object's x/y basede on the horizontal acceleration
    x += Xa;
    y += Ya;
    //update the hitbox's coordinates to match those of the player
    hitBox.x=(int)x;
    hitBox.y=(int)y;
    
    if(health<0){
    die();
    }
    }
    //reverts the player to standing position when they are not moving
    if(velocity==0){
    curFrame = 0;
    }
    footHitBox.y=(int)y+62;
    footHitBox.x=(int)x;
    headHitBox.y = (int)y;
    headHitBox.x = (int)x;
    SSRB.setXOffset(x);
    SSRB.setYOffset(y);
  }
  
  public void paint(Graphics2D g2d){
    if(isActive){
    //If player is dodging, they are slightly transparent.
    if(dodging){
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
      g2d.setComposite(alphaComposite);
    }
    
    super.paint(g2d);
    //make the player opaque again
    if(dodging){
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
      g2d.setComposite(alphaComposite);
    } 
    }
    if(SSRB.getDebug()){
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
      g2d.setColor(Color.BLUE);
      g2d.fill(footHitBox);
      g2d.fill(headHitBox);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
  }
  
   public void footCollide(GraphicsObject toCollide){
    while(footHitBox.intersects(toCollide.getHitBox())){
      double distDown = (toCollide.getY() + toCollide.getHeight()) - footHitBox.y;
      double distUp =  (footHitBox.y + footHitBox.height) - toCollide.getY();
      double distRight = (toCollide.getX() + toCollide.getWidth()) - footHitBox.x;
      double distLeft = (footHitBox.x + footHitBox.width) - toCollide.getX();
      
      if(distDown < distUp && distDown < distRight && distDown < distLeft) {
        y += distDown;
        hitBox.y += distDown;
        footHitBox.y += distDown;
        headHitBox.y += distDown;
      }
      else if(distLeft < distUp && distLeft < distRight) {
        x -= distLeft;
        hitBox.x -= distLeft;
        footHitBox.x -= distLeft;
        headHitBox.x -= distLeft;
      }
      else if(distRight < distUp) {
        x += distRight;
        hitBox.x += distRight;
        footHitBox.x += distRight;
        headHitBox.x += distRight;
      }
      else {
        y -= distUp;
        hitBox.y -= distUp;
        footHitBox.y -= distUp;
        headHitBox.y -= distUp;
      }
    }
  }
  
  
  //damage the player
  public void setHealth(int damage){
    if(hitInvinciblityTimer == 100){
      super.setHealth(damage);
      hitInvinciblityTimer = 0;
    }
    if(health>100){
      health = 100;
    }
  }
  //return true if the player is dodging
  public boolean getDodging(){
    return dodging;
  }
  public void setVelocity(int velocity){
    this.velocity = velocity;
  }
  public Rectangle getHeadHitBox(){
    return headHitBox;
  }
}