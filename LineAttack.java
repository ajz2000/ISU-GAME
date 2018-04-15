import java.awt.*;

public class LineAttack{
  private Polygon attack;
  private int currentHeight = 5;
  private double angle;
  private double width;
  private double height;
  private boolean active = true;
  int[] xPoints;
  int[] yPoints;
  
  public LineAttack(double midX, double midY, double angle){
    width = 64;
    height = 400;
    this.angle = angle;
    xPoints = new int[4];
    yPoints = new int[4];
    
    xPoints[0] = (int)(midX + ((width/2) * Math.cos(Math.toDegrees(angle + 90))));
    xPoints[1] = (int)(midX + ((width/2) * Math.cos(Math.toDegrees(angle - 90))));
    xPoints[2] = (int)(xPoints[0] + (currentHeight * Math.cos(Math.toRadians(angle))));
    xPoints[3] = (int)(xPoints[1] + (currentHeight * Math.cos(Math.toRadians(angle))));
    
    yPoints[0] = (int)(midY + ((width/2) * Math.sin(Math.toDegrees(angle + 90))));
    yPoints[1] = (int)(midY + ((width/2) * Math.sin(Math.toDegrees(angle - 90))));
    yPoints[2] = (int)(yPoints[0] + (currentHeight * Math.sin(Math.toRadians(angle))));
    yPoints[3] = (int)(yPoints[1] + (currentHeight * Math.sin(Math.toRadians(angle))));
    
    attack = new Polygon(xPoints, yPoints, 4);
  }
  
  public void move(){
    currentHeight++;
    
    if(currentHeight > height){
      active = false;
    }
    
    xPoints[3] = (int)(xPoints[0] + (currentHeight * Math.cos(Math.toRadians(angle))));
    xPoints[2] = (int)(xPoints[1] + (currentHeight * Math.cos(Math.toRadians(angle))));
    
    yPoints[3] = (int)(yPoints[0] + (currentHeight * Math.sin(Math.toRadians(angle))));
    yPoints[2] = (int)(yPoints[1] + (currentHeight * Math.sin(Math.toRadians(angle))));
    
    attack = new Polygon(xPoints, yPoints, 4);
  }
  
  public void paint(Graphics2D g2d){
    g2d.setColor(Color.CYAN);
    g2d.fill(attack);
  }
  
  public boolean collide(PlayerCharacter pc){
    return attack.intersects(pc.getHitBox());
  }
  
  public boolean getActive(){
    return active;
  }
}