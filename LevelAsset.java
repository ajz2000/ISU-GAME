import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelAsset extends GraphicsObject{
  
  private BufferedImage bgLayer = null;
  private BufferedImage fgLayer = null;
  private char[][] levelArray;
  private SSRB ssrb;
  private boolean custom;
  
  public LevelAsset(String levelName, SSRB ssrb){
    try {
      bgLayer = ImageIO.read(new File(levelName + "Back.png"));
      fgLayer = ImageIO.read(new File(levelName + "Front.png"));
    } catch (IOException e) {
      
    } 
    width = bgLayer.getWidth();
    height = bgLayer.getHeight();
    this.ssrb = ssrb;
    custom = false;
    loadCollision(levelName);
  }
  
  public LevelAsset(String levelName, File level, SSRB ssrb){
    try {
      BufferedImage bgInput = ImageIO.read(new File("BackgroundBig3.png"));
      Image bgScaled = bgInput.getScaledInstance(bgInput.getWidth() * 2, bgInput.getHeight() * 2, BufferedImage.SCALE_DEFAULT);
      bgLayer = new BufferedImage(bgScaled.getWidth(null), bgScaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
      bgLayer.createGraphics().drawImage(bgScaled, 0, 0, null);
      fgLayer = ImageIO.read(level);
    } catch (IOException e) {
    }
    width = fgLayer.getWidth();
    height = fgLayer.getHeight();
    this.ssrb = ssrb;
    custom = true;
    loadCollision(levelName);
  }
  
  public void paintBG(Graphics2D g2d){
    if(custom){
      g2d.drawImage(bgLayer,-500,-500,null);
    }else{
      g2d.drawImage(bgLayer,0,0,null);
    }
  }
  public void paintFG(Graphics2D g2d){
    g2d.drawImage(fgLayer,0,0,null);
  }
  
    public void loadCollision(String levelName){
    
    int tileHeight = 0;
    int tileWidth = 0;
    int currentLine = 0;
    String line = "";
    
    try{
      FileReader fr = new FileReader(levelName + ".txt");
      LineNumberReader lnr = new LineNumberReader(fr);
      
      
      while((line = lnr.readLine()) != null){
        tileHeight++;
        tileWidth = line.length();        
      }

      levelArray = new char[tileHeight][tileWidth];
      line = "";
      
      FileReader fr2 = new FileReader(levelName + ".txt");
      BufferedReader br = new BufferedReader(fr2);
      
      while ((line=br.readLine()) != null){
        for(int i = 0; i<= tileWidth-1; i++){
          levelArray[currentLine][i] = line.charAt(0);
          line = line.substring(1);
        }
        currentLine++;
      }
            
      lnr.close();
      br.close();
    } catch (Exception e){
      System.out.println("Error on collision load");
    }
    
    int wallHeight = 0;
    
    for(int i = 0; i <= tileWidth-1; i++){
      for(int j = 0; j <= tileHeight-1; j++){
        if(levelArray[j][i] == 'x'){
          wallHeight = 32;
          
          while(j < levelArray.length - 1){
            if(levelArray[j + 1][i] != 'x'){
              break;
            }
            wallHeight += 32;
            j++;
          }
          
          ssrb.addWall(new Wall(i*32,j*32 - (wallHeight - 32),32,wallHeight, false));
        }
        
        if(levelArray[j][i] == 'p'){
          wallHeight = 32;
          
          while(j < levelArray.length - 1){
            if(levelArray[j + 1][i] != 'p'){
              break;
            }
            wallHeight += 32;
            j++;
          }
          
          ssrb.addWall(new Wall(i*32,j*32 - (wallHeight - 32),32,wallHeight, true));
        }
      }
    }

  }
}

