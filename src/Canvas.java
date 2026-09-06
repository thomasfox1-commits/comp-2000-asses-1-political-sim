import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Canvas extends JFrame{
    Grid gameGrid;
    Color white = new Color(207,207,207);
    Color darkGrey = new Color(110,110,110);
    public Canvas(){
        setSize(800,1000);
        setTitle("politicalSim");
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setBackground(white);
        gameGrid= new Grid();
        gameGrid.setSize(800,800);
        gameGrid.setLocation(0,200);
        gameGrid.setBackground(darkGrey);
        add(gameGrid);
        setUpWindowCloser();
             
    }
    public Canvas(Grid grid, int width, int height, String title){
        setSize(width,height);
        setTitle(title);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        gameGrid= grid;
        add(gameGrid);
        setUpWindowCloser();
    }

    public void setUpWindowCloser(){
        addWindowListener(new WindowAdapter(){
            @Override 
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        }  );
    }
}
