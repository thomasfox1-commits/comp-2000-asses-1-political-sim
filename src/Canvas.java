import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Canvas extends JFrame{
    public Canvas(){
        setSize(1000,1000);
        setTitle("politicalSim");
        setLayout(null);
        setVisible(true);
        
        addWindowListener(new WindowAdapter(){
            @Override 
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        }  );     
    }
}
