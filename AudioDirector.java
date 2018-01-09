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
      
      inputStreamList.add(AudioSystem.getAudioInputStream(bassUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(drumUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(longPadUrl));
//      inputStreamList.add(AudioSystem.getAudioInputStream(lowHealthUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(mainKeyUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(melodyKeyUrl));
      inputStreamList.add(AudioSystem.getAudioInputStream(secondaryKeyUrl));
      
      
      for(int i = 0; i < inputStreamList.size(); i++){
        clipList.add(AudioSystem.getClip());
        clipList.get(clipList.size() - 1).open(inputStreamList.get(clipList.size() - 1));
        clipList.get(clipList.size() - 1).loop((int)(clipList.get(clipList.size() - 1).getMicrosecondLength()));
      }
      
      for(int i = 0; i < clipList.size(); i++){
        controlList.add((FloatControl) clipList.get(controlList.size() - 1).getControl(FloatControl.Type.MASTER_GAIN));
      }
    } catch(Exception e){
      System.out.println("Oh boy, definitely not running in the 90's");
    }
  }
  
  public void start(){
    for(int i = 0; i < clipList.size(); i++){
      clipList.get(i).start();
    }
  }
}