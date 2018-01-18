import java.util.*;
import java.awt.*;
import java.io.*;

public class Director{
  private SSRB ssrb;
  private PlayerCharacter pc;
  private int wave = 0;
  private int basicMultiplier = 1;
  private int rangedMultiplier = 1;
  private int explodeMultiplier = 1;
  private int spawnerMultiplier = 1;
  private Stack<Enemy> spawnStack;
  private int baseEnemies;
  private int addBase;
  private int newWaveTimer = 0;
  
  public Director(SSRB ssrb,PlayerCharacter pc){
    this.ssrb = ssrb;
    this.pc = pc;
    
    basicMultiplier = 3;
    rangedMultiplier = 0;
    explodeMultiplier = 0;
    spawnerMultiplier = 0;
    calculateEnemies();
  }
  public void paint(Graphics2D g2d){
    if(!ssrb.getAtMenu() && newWaveTimer < 50){
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (1.0f - (float)(newWaveTimer/50f)) * 1.0f);
      g2d.setComposite(alphaComposite);
      g2d.setColor(Color.WHITE);
      g2d.fillRect(-5000,-5000,SSRB.getScreenWidth()*10,SSRB.getScreenHeight()*10);
      g2d.setColor(Color.RED);
      g2d.setFont(new Font("TimesRoman", Font.BOLD, 64));
      g2d.translate(SSRB.getXOffset(),SSRB.getYOffset());
      g2d.drawString("Wave: " + Integer.toString(wave), 0 - g2d.getFontMetrics().stringWidth("Wave: " + Integer.toString(wave)) / 2, 0);
      g2d.translate(-SSRB.getXOffset(),-SSRB.getYOffset());
      alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
      g2d.setComposite(alphaComposite);
      newWaveTimer++;
    }
  }
  public int checkHighScore(){
    try{
    FileReader fr = new FileReader("Score.txt");
      BufferedReader br = new BufferedReader(fr);
      int toReturn = Integer.parseInt(br.readLine());
      fr.close();
      br.close();
            return toReturn;
    } catch(Exception e){
      System.out.println("error checking high score");
      return 0;
    }
  }
  
    public void setHighScore(int toSet){
      try{
    FileWriter fw = new FileWriter("Score.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.println(toSet);
      fw.close();
      pw.close();
      } catch(Exception e){
        System.out.println("error writing high score to file");
      }
    }
    
  public void spawnEnemies(){
    int spawnX = 0;
    int spawnY = 0;
    
    for(int i = 0; i < baseEnemies * basicMultiplier; i++){
      ssrb.addEnemy(new EnemyBasic(spawnX,spawnY,pc,ssrb));
      spawnX = (int)((Math.random() * 2000) + 1);
      spawnY = (int)((Math.random() * 2000) + 1);
    }
    for(int i = 0; i < baseEnemies * rangedMultiplier; i++){
      ssrb.addEnemy(new EnemyShooting(spawnX,spawnY,pc,ssrb));
      spawnX = (int)((Math.random() * 2000) + 1);
      spawnY = (int)((Math.random() * 2000) + 1);
    }
    for(int i = 0; i < baseEnemies * explodeMultiplier; i++){
      ssrb.addEnemy(new EnemyExploding(spawnX,spawnY,pc,ssrb));
      spawnX = (int)((Math.random() * 2000) + 1);
      spawnY = (int)((Math.random() * 2000) + 1);
    }
    for(int i = 0; i < baseEnemies * spawnerMultiplier; i++){
      ssrb.addEnemy(new EnemySpawning(spawnX,spawnY,pc,ssrb));
      spawnX = (int)((Math.random() * 2000) + 1);
      spawnY = (int)((Math.random() * 2000) + 1);
    }
  }
  public void calculateEnemies(){
    wave++;
    if(wave> checkHighScore()){
      setHighScore(wave);
    }
    if(wave%3 == 1){
      if(wave == 1){
        basicMultiplier = 3;
        rangedMultiplier = 0;
        explodeMultiplier = 0;
        addBase = 1;
      }
      else if(wave == 4){
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 2;
      }
      else{
        addBase += 5;
        basicMultiplier = 3;
        rangedMultiplier = 1;
        explodeMultiplier = 1;
        spawnerMultiplier = 1;
      }
    }
    else if(wave%3 == 2){
      if(wave == 2){
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 0; 
      }
      else if(wave == 5){
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 2;
      }
      else{
        
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 2;
        spawnerMultiplier = 1;
      }
    }
    else if(wave%3 == 0){
      if(wave == 3){
        basicMultiplier = 2;
        rangedMultiplier = 2;
        explodeMultiplier = 3;
      }
      else{
        basicMultiplier = 2;
        rangedMultiplier = 2;
        explodeMultiplier = 3;
        spawnerMultiplier = 2;
      }
    }
    baseEnemies = (int)((Math.random() * 3) + addBase);
    newWaveTimer = 0;
    spawnEnemies();
  }
  
  public void reset(){
    wave = 0;
    basicMultiplier = 3;
    rangedMultiplier = 0;
    explodeMultiplier = 0;
    spawnerMultiplier = 0;
    calculateEnemies();
  }
  
  public int getWave(){
    return wave;
  }
}