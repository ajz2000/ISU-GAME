public class Pickup extends GraphicsObject{
  protected int value;
  protected int type;
  public void collect(){
    isActive = false;
  }
  public int getValue(){
    return value;
  }
    public int getType(){
    return type;
  }
}