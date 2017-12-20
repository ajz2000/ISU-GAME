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
    
    x = 20;
    y = 4;
    width = 32;
    height = 16;
    
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
    g2d.fillRect(4,4,4,4);
    g2d.fillRect(4,210,4,4);
    g2d.setColor(Color.RED);
    for(int i = pc.getHealth(); i>=1; i--){
      g2d.fillRect(4,(200-i*2)+8,4,4);
    }
    //limit break bar
    g2d.setColor(Color.WHITE);
    g2d.fillRect(8,4,4,4);
    g2d.fillRect(8,112,4,4);
     g2d.setColor(Color.ORANGE);
    for(int i = rc.getLimitBreak(); i>=1; i--){
      g2d.fillRect(8,(100 -((i/40)*2))+8,4,4);
    }
    //Gun Image
    g2d.setColor(Color.ORANGE);
    switch(rc.getCurrentGun()){
      case 0:
        sprite = imageList.get(0);
        g2d.drawString("Unlimited",20,30);
        break;
      case 1:
        sprite = imageList.get(1);
        g2d.drawString(Integer.toString(rc.getAmmo(1)),20,30);
        break;
      case 2:
        sprite = imageList.get(2);
        g2d.drawString(Integer.toString(rc.getAmmo(2)),20,30);
        break;
      case 3:
        sprite = imageList.get(3);
        g2d.drawString(Integer.toString(rc.getAmmo(3)),20,30);
        break;
      default:
        System.out.println("WHY ARE YOU ON GUN TYPE 4?!?");
    }
    super.paint(g2d);
  }
}