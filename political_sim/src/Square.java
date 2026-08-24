import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Square extends JPanel{
    @Override
    public void paintComponent(Graphics g){
        System.out.println("hello");
        g.setColor(Color.blue);
        g.fillRect(100, 100, 100, 100);
    }
}
