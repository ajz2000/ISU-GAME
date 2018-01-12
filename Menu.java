import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image; 
import java.awt.image.BufferedImage;

public class Menu{
  private ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>();
  private ArrayList<Point> imagePointList = new ArrayList<Point>();
  private ArrayList<String> textList = new ArrayList<String>();
  private ArrayList<JButton> buttonList = new ArrayList<JButton>();
  private int currentMenu = 0;
  
  public Menu(int menuNum){
    currentMenu = menuNum;
    try{
      switch(menuNum){
        case 0:
          
          imageList.add(ImageIO.read(new File("MenuMain.png")));
          imagePointList.add(new Point((SSRB.getScreenWidth() / 2) - (imageList.get(0).getWidth() / 2), (SSRB.getScreenHeight() / 2) - (imageList.get(0).getHeight() / 2)));
          //imageList.get(0) - Main Menu
          break;
        case 1:
          break;
        case 2:
          break;
        case 3:
          imageList.add(ImageIO.read(new File("MenuPause.png")));
          imagePointList.add(new Point((SSRB.getScreenWidth() / 2) - imageList.get(0).getWidth() ,0));
          //imageList.get(3) - Pause Menu
          break;
        case 4:
           imageList.add(ImageIO.read(new File("MenuDead.png")));
           imagePointList.add(new Point((SSRB.getScreenWidth() / 2) - (imageList.get(0).getWidth() / 2), (SSRB.getScreenHeight() / 2) - (imageList.get(0).getHeight() / 2)));
          //game over
        default:
          break;
      }
    } catch(Exception e){
      
    }
  }
  
  public void paint(Graphics2D g2d){
    if(currentMenu == 3){
    g2d.translate(SSRB.getXOffset()-SSRB.getScreenWidth()/4,SSRB.getYOffset()-SSRB.getScreenHeight()/4);
    }
    for(int i = 0; i < imageList.size(); i++){
      g2d.drawImage(imageList.get(i),(int)(imagePointList.get(i).getX()),(int)(imagePointList.get(i).getY()),null);
    }
  }
  
  public void switchScreen(int toSwitch){
    
  }
  
  public int getMenu(){
    return currentMenu;
  }
}