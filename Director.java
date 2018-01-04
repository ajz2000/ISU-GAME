import java.util.*;

public class Director{
  private int wave = 0;
  private int basicMultiplier = 1;
  private int rangedMultiplier = 1;
  private int explodeMultiplier = 1;
  private Stack<Enemy> spawnStack;
  private int baseEnemies;
  
  public Director(){
    baseEnemies = (int)((Math.random() * 5) + 1);
    basicMultiplier = 3;
    rangedMultipler = 0;
    explodeMultiplier = 0;
    spawnEnemies();
  }
  
  private void spawnEnemies(){
    int spawnX = 0;
    int spawnY = 0;
    
    for(int i = 0; i < baseEnemies * basicMultiplier; i++){
      baseEnemies = (int)((Math.random() * 5) + 1);
    }
  }
}