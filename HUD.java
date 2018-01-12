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
  
  public void paint (Graphics2D g2d, SSRB ssrb){
    g2d.translate(SSRB.getXOffset(),SSRB.getYOffset());
    
    //Health Bar
    g2d.setColor(Color.RED);
    for(int i = pc.getHealth(); i>=1; i--){
      g2d.fillRect(0+i/4,-4,1,2);
    }
    //limit break bar
     g2d.setColor(Color.ORANGE);
    for(int i = rc.getLimitBreak(); i>=1; i--){
      g2d.fillRect(0+i/80,-6,1,2);
    }
    
    x = rc.getX() - SSRB.getXOffset();
    y = rc.getY() - SSRB.getYOffset() - 8;
    
    //Gun Image
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("TimesRoman", Font.BOLD, 6));
    switch(rc.getCurrentGun()){
      case 0:
        sprite = imageList.get(0);
        g2d.drawString("Unlimited",(int)(x + 17),(int)y + 4);
        break;
      case 1:
        sprite = imageList.get(1);
        g2d.drawString(Integer.toString(rc.getAmmo(1)),(int)(x + 17),(int)y + 4);
        break;
      case 2:
        sprite = imageList.get(2);
        g2d.drawString(Integer.toString(rc.getAmmo(2)),(int)(x + 18),(int)y + 4);
        break;
      case 3:
        sprite = imageList.get(3);
        g2d.drawString(Integer.toString(rc.getAmmo(3)),(int)(x + 17),(int)y + 4);
        break;
      default:
        System.out.println("WHY ARE YOU ON GUN TYPE 4?!?");
    }
    
    super.paint(g2d);

    g2d.translate(-SSRB.getXOffset(),-SSRB.getYOffset());
  }
}