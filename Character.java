import javax.sound.sampled.Clip;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character extends MovableObject{

  protected int health;
  protected Clip audio;
  protected ArrayList<AudioInputStream> soundList;

  public int getHealth(){
    return health;
  }
  //sets health of the character
  public void setHealth(int damage){
    health -= damage;
    //kill the character
    if(health < 0){
      die();
    }
  }
  //set the character as inactive
  public void die(){
    isActive = false;
  }
}