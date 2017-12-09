import java.awt.*;

public class HUD extends GraphicsObject{
  
  private PlayerCharacter pc;
  
  public HUD(PlayerCharacter pc){
  this.pc = pc;
  }
  
  public void paint (Graphics2D g2d){
    g2d.setColor(Color.GRAY);
    g2d.fillRect(2*SSRB.getScaleRatio(),2*SSRB.getScaleRatio(),2*SSRB.getScaleRatio(),2*SSRB.getScaleRatio());
    g2d.fillRect(2*SSRB.getScaleRatio(), 105*SSRB.getScaleRatio(),2*SSRB.getScaleRatio(),2*SSRB.getScaleRatio());
    g2d.setColor(Color.RED);
    for(int i = pc.getHealth(); i>=1; i--){
      g2d.fillRect(2*SSRB.getScaleRatio(),(100-i)*SSRB.getScaleRatio()+4*SSRB.getScaleRatio(),2*SSRB.getScaleRatio(),2*SSRB.getScaleRatio());
    }
  }
}