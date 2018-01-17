import java.awt.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.util.*;


public class AudioDirector{
  private ArrayList<AudioInputStream> inputStreamList = new ArrayList<AudioInputStream>();
  private ArrayList<Clip> clipList = new ArrayList<Clip>();
  private ArrayList<FloatControl> controlList = new ArrayList<FloatControl>();
  private SSRB ssrb;
  private int totalEnemiesThisWave = 1;
  
  public AudioDirector(SSRB ssrb) {
    this.ssrb = ssrb;
    try{
      
      
      URL bassUrl = this.getClass().getClassLoader().getResource("Bass.wav");
      URL drumUrl = this.getClass().getClassLoader().getResource("Drums.wav");
      URL longPadUrl = this.getClass().getClassLoader().getResource("Long Pad.wav");
      URL lowHealthUrl = this.getClass().getClassLoader().getResource("Low Health.wav");
      URL mainKeyUrl = this.getClass().getClassLoader().getResource("Main Key.wav");
      URL melodyKeyUrl = this.getClass().getClassLoader().getResource("Melody Key.wav");
      URL secondaryKeyUrl = this.getClass().getClassLoader().getResource("Secondary Key.wav");
      URL menuMusicUrl = this.getClass().getClassLoader().getResource("Main Menu.wav");
      
      inputStreamList.add(AudioSystem.getAudioInputStream(bassUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(drumUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(longPadUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(lowHealthUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(mainKeyUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(melodyKeyUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(secondaryKeyUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(menuMusicUrl));
      
      
      for(int i = 0; i < inputStreamList.size(); i++){
        clipList.add(AudioSystem.getClip());
        clipList.get(clipList.size() - 1).open(inputStreamList.get(clipList.size() - 1));
        clipList.get(clipList.size() - 1).loop((int)(clipList.get(clipList.size() - 1).getMicrosecondLength()));
      }
      
      for(int i = 0; i < clipList.size(); i++){
        controlList.add((FloatControl) clipList.get(i).getControl(FloatControl.Type.MASTER_GAIN));
      }
      
      System.out.println(controlList.get(1).getMaximum());
      System.out.println(controlList.get(1).getMinimum());
      System.out.println(controlList.get(1).getUnits());
      
      controlList.get(3).setValue(-80f);
    } catch(Exception e){
      System.out.println("Oh boy, definitely not running in the 90's");
      e.printStackTrace();
    }
  }
  
  public void start(){
    for(int i = 0; i < clipList.size(); i++){
      clipList.get(i).start();
      controlList.get(i).setValue(-80);
    }
  }

  
  public void setVolume(){
    float enemyBasicNum = 0f;
    float enemyShootingNum = 0f;
    float enemyExplodingNum = 0f;
    float enemySpawningNum = 0f;
    
    ArrayList<Enemy> enemyList = ssrb.getEnemyList();
    
    float maxEnemies = (float)enemyList.size();
    
    for(int i = 0; i < enemyList.size(); i++){
      if(enemyList.get(i) instanceof EnemyBasic){
        enemyBasicNum++;
      }
      else if(enemyList.get(i) instanceof EnemyShooting){
        enemyShootingNum++;
      }
      else if(enemyList.get(i) instanceof EnemyExploding){
        enemyExplodingNum++;
      }
      else if(enemyList.get(i) instanceof EnemySpawning){
        enemySpawningNum++;
      }
      else if(enemyList.get(i) instanceof EnemySmall){
        maxEnemies--;
      }
    }
    
   
    if(ssrb.getAtMenu()){
      controlList.get(7).setValue(0);
      for(int i = 0; i < 7; i++){
     controlList.get(i).setValue(-80); 
      }
    }
    else{
      
    controlList.get(2).setValue(-4);
    controlList.get(7).setValue(-80);

    
    if(maxEnemies > 0){
      //Melody key set based on Basic enemies
      controlList.get(5).setValue(-17 + ((enemyBasicNum/maxEnemies) * 17));
      
       //bass key key set based on shooting enemies
      controlList.get(0).setValue(-17 + ((enemyShootingNum/maxEnemies) * 17));
      
      //Drums set based on number of enemies
      controlList.get(1).setValue(-20 + (((enemyBasicNum+enemyShootingNum+enemyExplodingNum+enemySpawningNum)/totalEnemiesThisWave) * 20));
      
      //main key set based on exploding enemies
      controlList.get(4).setValue(-17 + ((enemyExplodingNum/maxEnemies) * 17));
      
      //Secondary key set based on Spawning enemies
      controlList.get(6).setValue(-5 + ((enemyExplodingNum/maxEnemies) * 17));
    }
    
    if(ssrb.getPlayer().getHealth() < 25){
      controlList.get(3).setValue(0);
    }
    else{
      controlList.get(3).setValue(-80);
    }
    }
  }
  public void setTotalWaveEnemies(int toSet){
  totalEnemiesThisWave = toSet;
  }
}