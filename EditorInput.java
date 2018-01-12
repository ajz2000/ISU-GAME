import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

//This class initializes anonymous class, anonymous class holds functionality.
public class EditorInput{
  protected JFrame frame;
  private InputField i;
  protected boolean closed = false;
  protected LevelEditor le;
  //If create is true, create level, else, load level.
  protected boolean create;
  
  public EditorInput(LevelEditor le, boolean create){
    this.le = le;
    this.create = create;
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createFrame();
      }
    });
  }
  
  public boolean getClosed()
  {
    return closed;
  }
  
  public void createFrame(){
    //You know this
    frame = new JFrame("Editor Input");
    
    frame.setSize(500, 400);
    
    frame.add(i = new InputField());
    
    frame.setVisible(true);
    
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  
  public String getName()
  {
    return i.name; 
  }
  
  public String getLoad()
  {
    return i.loadName; 
  }
  
  public int getWidth()
  {
    return Integer.parseInt(i.width); 
  }
  
  public int getHeight()
  {
    return Integer.parseInt(i.height);
  }
  
  class InputField extends JPanel implements ActionListener
  {
    //"in" = Input in variable names.
    //Counter for how many times Enter has been pressed.
    int inCount = 0;
    
    private JTextField inField;
    
    private JTextArea displayArea;
    
    //Strings to store variables.
    String name;
    String height;
    String width;
    
    //String for file to load.
    String loadName;
    
    public InputField()
    {
      super(new GridBagLayout());
      
      //Create and initialize JTextField, # param is chars it will show.
      inField = new JTextField(20);
      
      //Add the actionlistener to the field so they can take inputs (this class is an ActionListener.
      inField.addActionListener(this);
      
      //Initialize JTextArea and print initial text
      displayArea = new JTextArea("Name: \n", 20, 6);
      
      GridBagConstraints c = new GridBagConstraints();
      c.weightx = 1.0;
      c.weighty = 1.0;
      c.anchor = GridBagConstraints.NORTHWEST;
      
      add(inField, c);
      
      c.gridx = 0;
      add(displayArea, c);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if(create){
        switch(inCount)
        {
          case 0:
            //Set variable to text in JTextField
            name = inField.getText();
            //Show new text in JTextArea.
            displayArea.append(name + "\n");
            displayArea.append("Height: \n");
            //Highlight text.
            inField.selectAll();
            inCount++;
            break;
          case 1:
            //Set variable to text in JTextField
            height = inField.getText();
            //Show new text in JTextArea.
            displayArea.append(height + "\n");
            displayArea.append("Width: \n");
            //Highlight text.
            inField.selectAll();
            inCount++;
            break;
          case 2:
            //Set variable to text in JTextField
            width = inField.getText();
            //Show new text in JTextArea.
            displayArea.append(width + "\n");
            displayArea.append("Press enter to close.");
            //Highlight text.
            inField.selectAll();
            inCount++;
            break;
          default:
            //Get rid of frame.
            frame.dispose();
            //Finish creating in LevelEditor.
            le.finishCreate();
        }
      }
      else{
        switch(inCount)
        {
          case 0:
            //Set variable to text in JTextField
            loadName = inField.getText();
            //Show new text in JTextArea.
            displayArea.append(loadName + "\n");
            displayArea.append("Press enter to close.");
            inCount++;
            break;
          default:
            //Get rid of frame.
            frame.dispose();
            //Finish loading in LevelEditor.
            le.finishLoad();
        }
      }
    }
  }
}