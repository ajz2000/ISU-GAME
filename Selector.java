import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Selector{
  
  private int x = 1;
  private int y = 1;
  private int mouseX = 1;
  private int mouseY = 1;
  private Boolean showDebug = false;
  private LevelEditor le;
  private char tileType = 'o';
  //Holds which tile is being written over, so code doesn't stop when it's filling ceiling with walls if it started on a ceiling
  private char fillTile = 'o';
  
  public Selector(LevelEditor le){
    this.le = le;
  }
  
  public void keyPressed(KeyEvent e) {
    System.out.println("Called");
    if (e.getKeyCode() == KeyEvent.VK_SPACE){
    }
    if (e.getKeyCode() == KeyEvent.VK_A){
      if(x-1>=1 && !le.getMouseMode())
        x--;
    }
    if (e.getKeyCode() == KeyEvent.VK_W){
      if(y-1>=1 && !le.getMouseMode())
        y--;
    }
    if (e.getKeyCode() == KeyEvent.VK_S){
      if(y+1<LevelEditor.height + 1 && !le.getMouseMode())
        y++;
    }
    if (e.getKeyCode() == KeyEvent.VK_D){
      if(x+1<LevelEditor.width + 1 && !le.getMouseMode())
        x++;
    }
    if (e.getKeyCode() == KeyEvent.VK_Q){
      showDebug = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_2&&le.getLoaded()==true){
      if(!le.getMouseMode()){
        le.setLevelArray(y, x, 'x');
      }
      tileType = 'x';
    }
    if (e.getKeyCode() == KeyEvent.VK_1&&le.getLoaded()==true){
      if(!le.getMouseMode()){
        le.setLevelArray(y, x, 'o');
      }
      tileType = 'o';
    }
    if (e.getKeyCode() == KeyEvent.VK_3&&le.getLoaded()==true){
      if(!le.getMouseMode()){
        le.setLevelArray(y, x, 't');
      }
      tileType = 't';
    }
    if (e.getKeyCode() == KeyEvent.VK_4&&le.getLoaded()==true){
      if(!le.getMouseMode()){
        //le.setLevelArray(y, x, 'c');
      }
      //tileType = 'c';
    }
  }
  public void debug(Graphics2D g2d){
    g2d.drawString("x: " + x,50,50);
    g2d.drawString("y: " + y,50,60);
    if(le.getMouseMode()){
      g2d.drawString("Mouse Mode: On", 50, 70);
      g2d.drawString("Mouse Tile Type: " + tileType, 50, 80);
    }
    else{
      g2d.drawString("Mouse Mode: Off", 50, 70);
    }
  }
  public void draw(Graphics2D g2d){
    
    g2d.setColor(Color.BLACK);
    
    mouseX = ((int)(MouseInfo.getPointerInfo().getLocation().getX())) - ((int)(le.getLocationOnScreen().getX()));
    mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY()) - ((int)(le.getLocationOnScreen().getY()));
    
    if(le.getMouseMode()){
      if(mouseX > le.getTileSize() * LevelEditor.width + 1){
        mouseX = le.getTileSize() * (LevelEditor.width);
      }
      
      if(mouseX < le.getTileSize()){
        mouseX = le.getTileSize();
      }
      
      if(mouseY > le.getTileSize() * LevelEditor.height + 1){
        mouseY = le.getTileSize() * (LevelEditor.height);
      }
      
      if(mouseY < le.getTileSize()){
        mouseY = le.getTileSize();
      }
      
      x = (int)(mouseX / le.getTileSize());
      y = (int)(mouseY / le.getTileSize());
      
      g2d.drawRect(mouseX - (mouseX % le.getTileSize()), mouseY - (mouseY % le.getTileSize()),le.getTileSize(),le.getTileSize());
    }
    else
    {
      g2d.drawRect(x*le.getTileSize(), y*le.getTileSize(),le.getTileSize(),le.getTileSize());
    }
    if(showDebug == true)
      debug(g2d);
  }
  
  private int[] getTileCollision(){
    x = (mouseX - (mouseX % le.getTileSize())) / le.getTileSize();
    y = (mouseY - (mouseY % le.getTileSize())) / le.getTileSize(); 
    
    int[] i;
    
    if(x < LevelEditor.width + 1 && y < LevelEditor.height + 1){
      i = new int[] {y, x};
    }
    else{
      i = new int[] {-1, -1};
    }
    return i;
  }
  
  public void mousePressed(MouseEvent e) {
    int[] i = new int[] {-1, -1};
    
    int[] colTile = getTileCollision();
    if(!(Arrays.equals(colTile, i)))
    {
      if(e.getButton() == MouseEvent.BUTTON1){
        le.setLevelArray(colTile[0], colTile[1], tileType);
      }
      else if(e.getButton() == MouseEvent.BUTTON3){
        le.setLevelArray(colTile[0], colTile[1], 'o');
      }
    }
  }
  
  public void fill(char[][] levelArray) throws Exception{
    //Boolean to check when values are found.
    boolean found = false;
    int left = 0;
    int rowY =0;
    
    //First init and find the left of the current row
    if(le.getMouseMode()){
      left = (mouseX - (mouseX % le.getTileSize())) / le.getTileSize();
      rowY = (mouseY - (mouseY % le.getTileSize())) / le.getTileSize();
    }
    else{
      left = x;
      rowY = y;
    }
    
    //The tile you are writing over
    fillTile = le.getLevelArray(rowY, left);
    
    if(fillTile == tileType){
      throw new Exception();
    }
    
    //Find the left of the row.
    while(!found){
      //If x is 0 or the tile to the Left is the same type, the left is found.
      if(left == 1){
        found = true;
      }
      else if(levelArray[rowY][left - 1] != fillTile){
        found = true;
      }
      else{
        left--;
      }
    }
    
    //Now init and find the Right of the row to fill.
    int right = left;
    
    //Reset found.
    found = false;
    
    //Find right of row.
    while(!found){
      //If x is at the right of the array or the tile to the right is the same type, the right is found.
      if(right == LevelEditor.width){
        found = true;
      }
      else if(levelArray[rowY][right + 1] != fillTile){
        found = true;
      }
      else{
        right++;
      }
    }
    
    //Fill the line formed by the two points.
    for(int j = left; j <= right; j++){
      levelArray[rowY][j] = tileType;
      le.setLevelArray(rowY, j, tileType);
      
      //If there is a space above that is not the right tile, recursively call fill giving the location to an overloaded method, this also applies if
      //the tile below is not the right type.
      if(rowY != 1){
        if(levelArray[rowY - 1][j] == fillTile){
          fill(levelArray, rowY - 1, j);
        }
      }
      
      
      if(rowY != LevelEditor.height){
        if(levelArray[rowY + 1][j] == fillTile){
          fill(levelArray, rowY + 1, j);
        }
      }
    }
  }
  
  public void fill(char[][] levelArray, int startY, int startX){
    //Boolean to check when values are found.
    boolean found = false;
    //First init and find the left of the current row
    int left = startX;
    int rowY = startY;
    
    //Find the left of the row.
    while(!found){
      //If x is 0 or the tile to the Left is the same type, the left is found.
      if(left == 1){
        found = true;
      }
      else if(levelArray[rowY][left - 1] != fillTile){
        found = true;
      }
      else{
        left--;
      }
    }
    
    //Now init and find the Right of the row to fill.
    int right = left;
    
    //Reset found.
    found = false;
    
    //Find right of row.
    while(!found){
      //If x is at the right of the array or the tile to the right is the same type, the right is found.
      if(right == LevelEditor.width){
        found = true;
      }
      else if(levelArray[rowY][right + 1] != fillTile){
        found = true;
      }
      else{
        right++;
      }
    }
    
    //Fill the line formed by the two points.
    for(int j = left; j <= right; j++){
      levelArray[rowY][j] = tileType;
      le.setLevelArray(rowY, j, tileType);
      
      //If there is a space above that is not the right tile, recursively call fill giving the location to an overloaded method, this also applies if
      //the tile below(if at the bottom of the fill) is not the right type.
      if(rowY != 1){
        if(levelArray[rowY - 1][j] == fillTile){
          fill(levelArray, rowY - 1, j);
        }
      }
      
      
      if(rowY != LevelEditor.height){
        if(levelArray[rowY + 1][j] == fillTile){
          fill(levelArray, rowY + 1, j);
        }
      }
    }
  }
  
  public char getTileType(){
    return tileType;
  }
}