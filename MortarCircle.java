import java.awt.*;
import java.util.ArrayList;

public class MortarCircle{
  private int radius;
  private double x;
  private double y;
  private double centreX;
  private double centreY;
  private int lifeTimer = 0;
  private boolean active = true;
  private ArrayList<Mortar> mortarList = new ArrayList<Mortar>();
  private int spawnTimer = 0;
  
  public MortarCircle(double centreX, double centreY, int radius){
    this.radius = radius;
    
    x = centreX - radius;
    y = centreY - radius;
    this.centreX = centreX;
    this.centreY = centreY;
  }
  
  public void update(){
    lifeTimer++;
    spawnTimer++;
    
    if(lifeTimer > 3000){
      active = false;
    }
    
    if(spawnTimer > 5){
      spawnMortar();
      spawnTimer = 0;
    }
    
    for(int i = 0; i < mortarList.size(); i++){
      mortarList.get(i).update();
    }
    
    for(int i = 0; i < mortarList.size(); i++){
      if(!mortarList.get(i).getActive()){
        mortarList.remove(i);
      }
    }
  }
  
  public void paint(Graphics2D g2d){
    g2d.setColor(Color.RED);
    
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
    g2d.fillOval((int)x, (int)y, radius * 2, radius * 2);
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    
    g2d.drawOval((int)x, (int)y, radius * 2, radius * 2);
    
    for(int i = 0; i < mortarList.size(); i++){
      mortarList.get(i).paint(g2d);
    }
  }
  
  public boolean getActive(){
    return active;
  }
  
  public boolean collide(PlayerCharacter pc){
    for(int i = 0; i < mortarList.size(); i++){
      if(mortarList.get(i).collide(pc)){
        return true;
      }
    }
    
    return false;
  }
  
  public void spawnMortar(){
    double angle = (Math.random() * 361);
    double distance = Math.random() * radius;
    
    double spawnX = centreX + (distance * Math.cos(Math.toRadians(angle)));
    double spawnY = centreY + (distance * Math.sin(Math.toRadians(angle)));
    
    mortarList.add(new Mortar(spawnX, spawnY));
  }
}