import java.util.*;

public class Director{
  private SSRB ssrb;
  private PlayerCharacter pc;
  private int wave = 0;
  private int basicMultiplier = 1;
  private int rangedMultiplier = 1;
  private int explodeMultiplier = 1;
  private Stack<Enemy> spawnStack;
  private int baseEnemies;
  private int addBase;
  
  public Director(SSRB ssrb,PlayerCharacter pc){
    this.ssrb = ssrb;
    this.pc = pc;
    
    basicMultiplier = 3;
    rangedMultiplier = 0;
    explodeMultiplier = 0;
    calculateEnemies();
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
  }
  public void calculateEnemies(){
    wave++;
    
    if(wave%3 == 1){
      if(wave == 1){
        basicMultiplier = 3;
        rangedMultiplier = 0;
        explodeMultiplier = 0;
        addBase = 1;
      }
      else{
        addBase += 5;
        basicMultiplier = 3;
        rangedMultiplier = 1;
        explodeMultiplier = 1;
      }
    }
    else if(wave%3 == 2){
      if(wave == 2){
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 0; 
      }
      else{
        
        basicMultiplier = 2;
        rangedMultiplier = 3;
        explodeMultiplier = 2;
      }
    }
    else if(wave%3 == 0){
      basicMultiplier = 2;
        rangedMultiplier = 2;
        explodeMultiplier = 3;
    }
    baseEnemies = (int)((Math.random() * 5) + addBase);
    
    spawnEnemies();
  }
  
  public int getWave(){
    return wave;
  }
}