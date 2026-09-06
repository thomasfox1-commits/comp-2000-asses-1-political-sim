import java.awt.*;
import javax.swing.*;
public class Grid extends JPanel{
    public Grid(int width,int height, int gridSize){
        setLayout(null);
        Forest placeHolder;
        setSize(width,height);
        for(int i = 0;i<gridSize;i++){
            for(int x = 0;x<gridSize;x++){
                placeHolder=new Forest();
                placeHolder.setBackground(Color.black);
                placeHolder.setSize(31, 31);
                placeHolder.setLocation(i*width/gridSize,x*height/gridSize);
                add(placeHolder);
            }
        }
    }
    

    
}
