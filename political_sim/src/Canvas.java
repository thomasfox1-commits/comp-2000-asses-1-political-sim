import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Canvas extends JFrame {
    Canvas(){
        setTitle("cooler name");
        
        setSize(1000,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new Square());
        
        setVisible(true);
        //drawSquare();
        
    }
    public void drawSquare(){
        
        Graphics g =getGraphics();
        g.drawRect(10, 10, 100, 100);
        
        System.out.println("drawing");
    }
    
}
