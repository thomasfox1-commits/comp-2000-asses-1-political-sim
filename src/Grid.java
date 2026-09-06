import java.awt.*;
import javax.swing.*;
public class Grid extends JPanel{
    public Grid(int width,int height, int gridSize){
        Forest placeHolder;
        setSize(width,height);
        setLayout(null);
        int tileSizeHeight,tileSizeWidth;
        tileSizeWidth=width/gridSize;
        tileSizeHeight=height/gridSize;
        System.out.println("grid tile Width: " + tileSizeWidth + " Height: " + tileSizeHeight);
        for(int x = 0;x<gridSize;x++){
            for(int y = 0;y<gridSize;y++){
                placeHolder=new Forest();
                placeHolder.setBackground(Color.black);
                placeHolder.setSize(tileSizeWidth-1, tileSizeHeight-1);
                placeHolder.setLocation(x*tileSizeWidth , y*tileSizeHeight);
                add(placeHolder);
            }
        }
    }
    

    
}
