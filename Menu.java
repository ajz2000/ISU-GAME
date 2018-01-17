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
  private ArrayList<Point> textPointList = new ArrayList<Point>();
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
          imageList.add(ImageIO.read(new File("MenuLevelSelect.png")));
           imagePointList.add(new Point((SSRB.getScreenWidth() / 2) - (imageList.get(0).getWidth() / 2), (SSRB.getScreenHeight() / 2) - (imageList.get(0).getHeight() / 2)));
               //imageList.get(1) - Level Select
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
          break;
        case 5:
          imageList.add(ImageIO.read(new File("MenuHelp.png")));
          imagePointList.add(new Point((SSRB.getScreenWidth() / 2) - (imageList.get(0).getWidth() / 2), (SSRB.getScreenHeight() / 2) - (imageList.get(0).getHeight() / 2)));
          textList.add("There are robots trying ruin your day!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)));
          textList.add("Take your guns and ruin their day by brutally murdering them before they brutally murder you!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+20));
          textList.add("They are sentient and they feel pain!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+40));
          textList.add("Enemies can damage you by lowering your health!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+60));
          textList.add("If the health bar above your character hits zero you will be embraced by the sweet release of death!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+80));
          textList.add("All of your weapons except your pistol have limited ammuniton, collect mysterious black boxes to continue your roboto massacre!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+100));
          textList.add("Enemies come in waves!!! Listen carefully to the music to determine the makeup of the enemy ranks!!!");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+120));          
          textList.add("Once your orange bar charges up, you'll get one S U P E R S H O T with whatever gun you currently have equipped");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+140));         
          textList.add("THEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERME");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+160));                  
          textList.add("THEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERMETHEROBOTSAREAFTERME");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+180));                  
          textList.add("HELPMEHELPMEHELPMEHELPMEHELPMEHELPMEHELPME");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+180));
           textList.add("HELPMEHELPMEHELPMEHELPMEHELPMEHELPMEHELPME");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+200));
           textList.add("HELPMEHELPMEHELPMEHELPMEHELPMEHELPMEHELPME");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+220));          
           textList.add("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+240));          
           textList.add("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+270));
           textList.add("----------------------------------------------------------------------------------------------------");
           textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+270));          
           textList.add("                                                        ************))))))))))))))))++++++++++#############");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+270));
          
           textList.add("*****&&&&*******))$$$$))))))))))))))+++++|||||}{{}{}}+++++#############");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+290));          
           textList.add("*****    &&&&*******))      ))))))))))))))+ ++++|||||}{{}{}}+++++#############");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+290));
          
           textList.add("^^^^^^^");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+320));
          textList.add("|||||");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+320));
          textList.add("Space to dodge, WASD to move, Left Mouse to shoot, Right Mouse to change guns, Esc to pause");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+350));
          
          textList.add("good luck");
          textPointList.add( new Point((SSRB.getScreenWidth() / 3),(imageList.get(0).getHeight() / 2)+370));
          
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
    g2d.setColor(Color.BLACK);
    for(int i = 0; i < textList.size (); i++){
      g2d.drawString(textList.get(i), (int)(textPointList.get(i).getX()),(int)(textPointList.get(i).getY()));
    }
    
  }
  
  public void switchScreen(int toSwitch){
    
  }
  
  public int getMenu(){
    return currentMenu;
  }
}