import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import java.awt.image.BufferedImage;

public class HUD extends GraphicsObject{
  
  private PlayerCharacter pc;
  private RobotCompanion rc;
  private ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
  
  public HUD(PlayerCharacter pc, RobotCompanion rc){
    this.pc = pc;
    this.rc = rc;
    
    x = 6;
    y = 2;
    width = 16;
    height = 8;
    
    try{
    imageList.add(ImageIO.read(new File("PistolIcon1.png")));
    imageList.add(ImageIO.read(new File("ShotgunIcon1.png")));
    imageList.add(ImageIO.read(new File("SniperIcon1.png")));
    imageList.add(ImageIO.read(new File("MachineGunIcon1.png")));
    }
    catch(IOException e){
      System.out.println("Gun images cannot be loaded");
    }
  }
  
  public void paint (Graphics2D g2d){
    //Health Bar
    g2d.setColor(Color.WHITE);
    g2d.fillRect(2,2,2,2);
    g2d.fillRect(2,105,2,2);
    g2d.setColor(Color.RED);
    for(int i = pc.getHealth(); i>=1; i--){
      g2d.fillRect(2,(100-i)+4,2,2);
    }
    
    //Gun Image
    switch(rc.getCurrentGun()){
      case 0:
        sprite = imageList.get(0);
        break;
      case 1:
        sprite = imageList.get(1);
        break;
      case 2:
        sprite = imageList.get(2);
        break;
      case 3:
        sprite = imageList.get(3);
        break;
      default:
        System.out.println("WHY ARE YOU ON GUN TYPE 4?!?");
    }
    
    super.paint(g2d);
  }
}