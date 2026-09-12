import java.awt.*;
import java.util.*;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
public class Grid extends JPanel{
    TreeMap<Integer,Land> gameMap;
    int gridSize;
    ArrayList<Empire> factions;
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
        Land placeHolder;
        for(int y = 0;y<gridSize;y++){
            for(int x = 0;x<gridSize;x++){
                double terrainType = Math.random();
                if (terrainType <= 0.2){
                    placeHolder=new Forest();
                }
                else if (terrainType >= 0.9){
                    placeHolder=new Mountain();
                }
                else
                {
                    placeHolder=new Plains();
                }
                placeHolder.ID=tileID;
                placeHolder.setBackground(Color.black);
                placeHolder.setSize(tileSizeWidth-1, tileSizeHeight-1);
                placeHolder.setLocation(x*tileSizeWidth , y*tileSizeHeight);
                add(placeHolder);
                addTile(tileID,placeHolder);

                if(tileExist(tileID-1)&& tileID%gridSize!=1){
                    getTile(tileID-1).setRight(placeHolder);
                    placeHolder.setLeft(getTile(tileID-1));
                }
                if(tileExist(tileID-gridSize)){
                    getTile(tileID-gridSize).setBelow(placeHolder);
                    placeHolder.setAbove(getTile(tileID-gridSize));
                }
                tileID++;
            }
        }
        factions = new ArrayList<>();
        Empire test=new Empire(Color.GREEN, 1);
        Land startLand = getTile((int)Math.floor(Math.random()*gridSize*gridSize)+1);
        test.addTile(startLand);
        test.updateTiles();
        factions.add(test);
        Empire test2=new Empire(Color.RED, 2);
        while (startLand.owner != null)
        {
            startLand = getTile((int)Math.floor(Math.random()*gridSize*gridSize)+1);
        }
        test2.addTile(startLand);
        test2.updateTiles();
        factions.add(test2);
        Empire test3=new Empire(Color.BLUE, 3);
        while (startLand.owner != null)
        {
            startLand = getTile((int)Math.floor(Math.random()*gridSize*gridSize)+1);
        }
        test3.addTile(startLand);
        test3.updateTiles();
        factions.add(test3);
    }

    public void updateTurn(){
        for(int i = 0; i < factions.size(); i++)
        {
            Empire faction = factions.get(i);
            faction.updateTiles();
            if (Math.random() > 0.5 || faction.getImprovableCount() == 0)
            {
                for(int j = 0; j < faction.getTroops(); j++)
                {
                    claimRandomTile(faction);
                    faction.updateTiles();
                }
            }
            else
            {
                faction.improveTile();
            }

            System.out.println("Faction " + faction.getID() + " Troops: " + faction.getTroops());
            System.out.println("Faction " + faction.getID() + " Total Tiles: " + faction.getTileCount());
            System.out.println("Faction " + faction.getID() + " Improvable Tiles: " + faction.getImprovableCount());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }

    public Land getTile(int ID){
        return gameMap.get(ID);
    }

    public void addTile(int identifier, Land land){
        gameMap.put(identifier, land);
    }

    public boolean tileExist(int ID){
		return gameMap.containsKey(ID);
	}
    
    public void update(){

    }
    public void claimRandomTile(Empire faction){
        ArrayList<Land> list = faction.getPlayableBorderTiles();
        Land borderTile, tileToclaim;
        //int num2 =0;
        try
        {
            borderTile=list.get((int)(Math.random()*list.size()));
        }
        catch(IndexOutOfBoundsException e) 
        {
            factions.remove(faction);
            return;
        }
        list=borderTile.getAdjTiles();
        int num=(int)(Math.random()*list.size());
        tileToclaim=null;

        while(faction.isOwner(tileToclaim)||!faction.isClaimable(tileToclaim)){
            // System.out.println("tile i'm chcking from: "+ borderTile.ID+" adjTile trying to take = " +num+ " is Owner = "+ faction.isOwner(tileToclaim) + " is claimable = "+ faction.isClaimable(tileToclaim));
            // if(tileToclaim!=null){
            //     System.out.println(tileToclaim.ID);
            // }
            num=(int)(Math.random()*list.size());
            tileToclaim=list.get(num);
            // if(num2>=25){
            //     System.out.println("loop occured");
            //     return;
            // }
            // num2++;
        }
        //System.out.println("\ntile taken\n");
        faction.addTile(tileToclaim);
    }

    
}
