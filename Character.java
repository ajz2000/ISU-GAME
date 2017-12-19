import javax.sound.sampled.Clip;
import java.util.*;
import javax.sound.sampled.AudioInputStream;

public abstract class Character extends MovableObject{

  int health;
  Clip audio;
  ArrayList<AudioInputStream> soundList;
  
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