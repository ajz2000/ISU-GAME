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
  
  public void setHealth(int damage){
    health -= damage;
    
    if(health < 0){
      isActive = false;
    }
  }
}