import javax.imageio.*;
import java.io.*;
import java.awt.Graphics2D;

public class Boss extends Enemy{
  private int attackTimer = 0;
  private int curAttack = 0;
  private boolean lineAtkOn = false;
  private int lineAtkTimer = 0;
  private LineAttack lineAtk;
  private MortarCircle mortarAtk;
  
  public Boss(int x, int y, PlayerCharacter pc, SSRB ssrb){
    this.pc=pc;
    this.ssrb = ssrb;
    width = 60;
    height = 84; 
    this.x = (int)x;
    this.y = (int)y;
    maxVelocity = 2;
    health = 3000;
    damage = 40;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    deathAnimFrames = 4;
    try {
      sprite = ImageIO.read(new File("Boss.png"));
      deathSprite = ImageIO.read(new File("EnemyBasicDeath.png"));
    } catch (IOException e) {
    }
  }
  
  public void move(){
    if(mortarAtk != null){
      mortarAtk.update();
      
      if(mortarAtk.collide(pc)){
        pc.setHealth(40);
      }
      
      if(!mortarAtk.getActive()){
        mortarAtk = null;
      }
    }
    
    if(lineAtkOn){
      if(lineAtk == null){
        lineAtkTimer++;
      } else {
        lineAtk.move();
        if(lineAtk.collide(pc)){
          pc.setHealth(40);
        }
      }
      
      if(lineAtkTimer > 300){
        lineAtk = new LineAttack(x + (width/2), y + (height/2), angle);
        lineAtkTimer = 0;
      }
      
      if(lineAtk != null){
        if(!lineAtk.getActive()){
          lineAtk = null;
          lineAtkOn = false;
        }
      }
      
      if(health <= 0){
        die();
      }
    } else {
      attackTimer++;
      
      if(attackTimer > 40){
        attack();
        attackTimer = 0;
      }
      
      super.move();
    }
  }
  
  public void attack(){
    switch(curAttack){
      case 0:
        mortarAtk = new MortarCircle(x + width, y + width, 500);
        break;
      case 1:
        lineAtkOn = true;
        break;
      case 2:
        break;
      default:
        break;
    }
    
    curAttack++;
    if(curAttack > 2){
      curAttack = 0;
    }
  }
  
  public void paint(Graphics2D g2d){
    if(lineAtk != null){
      lineAtk.paint(g2d);
    }
    
    if(mortarAtk != null){
      mortarAtk.paint(g2d);
    }
    
    super.paint(g2d);
  }
}