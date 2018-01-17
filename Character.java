import javax.sound.sampled.Clip;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character extends MovableObject{

  protected int health;
  protected Clip audio;
  protected ArrayList<AudioInputStream> soundList;
  protected BufferedImage deathSprite;
  protected int deathAnimFrames;
  protected int deathAnimTimer = 0;
  public int getHealth(){
    return health;
  }
  //sets health of the character
  public void setHealth(int damage){
    health -= damage;
  }
  //set the character as inactive
  public void die(){
    isActive = false;
  }
}