import java.awt.*;

public class Wall extends GraphicsObject{
  private boolean isPit;
  public Wall(int x, int y, int width, int height, boolean isPit){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    hitBox.x = x;
    hitBox.y = y;
    hitBox.width = width;
    hitBox.height = height;
    this.isPit = isPit;
  }
  
  public void paint(Graphics2D g2d){
    if(SSRB.getDebug()){
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
      
      g2d.setColor(Color.GREEN);
      g2d.fill(hitBox);
      g2d.setColor(Color.ORANGE);
      g2d.draw(hitBox);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
  }
  
  public boolean isPit(){
    return isPit;
  }
}