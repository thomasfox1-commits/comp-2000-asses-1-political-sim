import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Grid extends JPanel{
    TreeMap<Integer,Land> gameMap;
    int gridSize;
    public <E extends Land> Grid(int width,int height, int gridSize){
        gameMap = new TreeMap<Integer,Land>();
        this.gridSize = gridSize;
        setSize(width,height);
        setLayout(null);;
        //System.out.println("grid tile Width: " + tileSizeWidth + " Height: " + tileSizeHeight);
        createGrid();

        
    }
    public void createGrid(){
        int tileSizeHeight,tileSizeWidth, tileID;
        tileSizeWidth=getWidth()/gridSize;
        tileSizeHeight=getHeight()/gridSize;
        tileID=1;
        Forest placeHolder;
        for(int x = 0;x<gridSize;x++){
            for(int y = 0;y<gridSize;y++){
                placeHolder=new Forest();
                placeHolder.ID=tileID;
                placeHolder.setBackground(Color.black);
                placeHolder.setSize(tileSizeWidth-1, tileSizeHeight-1);
                placeHolder.setLocation(x*tileSizeWidth , y*tileSizeHeight);
                add(placeHolder);
                addTile(tileID,placeHolder);
                tileID++;
            }
        }
        gameMap.get(4).setBackground(Color.BLUE);

    }
    public void addTile(int identifier, Land land){
        gameMap.put(identifier, land);
    }
    

    
}
