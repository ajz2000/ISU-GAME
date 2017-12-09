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
}