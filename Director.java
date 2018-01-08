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
  
  public Director(SSRB ssrb,PlayerCharacter pc){
    this.ssrb = ssrb;
    this.pc = pc;
    baseEnemies = (int)((Math.random() * 5) + 1);
    basicMultiplier = 3;
    rangedMultiplier = 0;
    explodeMultiplier = 0;
    spawnEnemies();
  }
  
  public void spawnEnemies(){
    wave++;
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
  private void calculateEnemies(){
  
  }
}