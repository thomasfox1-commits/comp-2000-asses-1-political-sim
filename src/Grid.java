import java.awt.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
public class Grid extends JPanel{

    private int gridSize;
    private TreeMap<Integer,Land> gameMap;

    private ArrayList<Empire> factions;

    private double forestFireExtinquishChance= 0.4;
    private double forestFireChance=0.2;
    



    public  Grid(int width,int height, int gridSize,int factCount){
        gameMap = new TreeMap<Integer,Land>();
        this.gridSize = gridSize;
        setSize(width,height);
        setLayout(null);
        //System.out.println("grid tile Width: " + tileSizeWidth + " Height: " + tileSizeHeight);
        createGrid(factCount);

    }
    public void createGrid(int factCount){
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
                    placeHolder.setBackground(Color.black);
                    placeHolder.drawLand(tileSizeWidth, tileSizeHeight);
                }
                else if (terrainType >= 0.9){
                    placeHolder=new Mountain();
                    placeHolder.setBackground(Color.gray);
                    placeHolder.drawLand(tileSizeWidth, tileSizeHeight);
                }
                else
                {
                    placeHolder=new Plains();
                    placeHolder.setBackground(Color.black);
                }
                placeHolder.setID(tileID);
                
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
        Land startLand = getTile((int)Math.floor(Math.random()*gridSize*gridSize)+1);
        for (int i = 0; i < factCount; i++)
        {
            int redVal = 100+(155/((int)Math.ceil(Math.cbrt(factCount)-1)))*(i%(int)Math.ceil(Math.cbrt(factCount)));
            int greenVal = 100+(155/((int)Math.ceil(Math.cbrt(factCount)-1)))*((int)(Math.floor(i/(int)Math.ceil(Math.cbrt(factCount))))%(int)Math.ceil(Math.cbrt(factCount)));
            int blueVal = 100+(155/((int)Math.ceil(Math.cbrt(factCount)-1)))*((int)(Math.floor(i/((int)Math.ceil(Math.cbrt(factCount))*(int)Math.ceil(Math.cbrt(factCount)))))%(int)Math.ceil(Math.cbrt(factCount)));
            Color factCol = new Color(redVal, greenVal, blueVal);

            Empire faction = new Empire(factCol, i+1);
            while (startLand.getOwner() != null || startLand instanceof Impassable)
            {
                startLand = getTile((int)Math.floor(Math.random()*gridSize*gridSize)+1);
            }
            faction.addTile(startLand);
            faction.updateTiles();
            factions.add(faction);
        }
    }
    private ArrayList<Land> getTiles(Predicate<Land> operation){
        Supplier<ArrayList<Land>> supplier = ArrayList::new;
        return gameMap.values().stream().parallel().filter(operation).collect(Collectors.toCollection(supplier)) ;

    }
    public void updateTurn(){
        // for (int i = 0; i < 20; i++)
        // {
        //     System.out.println();
        // }
        ForestFireCheck();
        

        ArrayList<Integer> skipTurns = new ArrayList<>();
        for(int i = 0; i < factions.size(); i++)
        {
            Empire faction = factions.get(i);
            if (!skipTurns.contains(i))
            {
                try
                {
                    faction.updateTiles();
                }
                catch (IndexOutOfBoundsException e)
                {
                    factions.remove(faction);
                    i--;
                    continue;
                }
                if (Math.random() > 0.5)
                {
                    for(int j = 0; j < faction.getTroops(); j++)
                    {
                        claimRandomTile(faction);
                        faction.updateTiles();
                    }
                }
                else
                {
                    if ((Math.random() > 0.1 || i >= factions.size()-1) && faction.getImprovableCount() != 0)
                    {
                        faction.improveTile();
                    }
                    else
                    {
                        int allianceInd = (int)Math.floor((factions.size()-i)*Math.random()+i);
                        faction.ally(factions.get(allianceInd));
                        factions.get(allianceInd).ally(faction);
                        skipTurns.add(allianceInd);
                    }
                }
            }

            // System.out.println("Faction " + faction.getID() + " Colour: " + faction.getColor().toString());
            // System.out.println("Faction " + faction.getID() + " Troops: " + faction.getTroops());
            // System.out.println("Faction " + faction.getID() + " Total Tiles: " + faction.getTileCount());
            // System.out.println("Faction " + faction.getID() + " Improvable Tiles: " + faction.getImprovableCount());
            // for (int j = 0; j < faction.getAllies().size(); j++)
            // {
            //     if (factions.contains(faction.getAllies().get(j)))
            //     {
            //         System.out.println("Faction " + faction.getID() + " Allied With " + faction.getAllies().get(j).getID());
            //     }
            // }
            // System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }
    public void ForestFireCheck(){
        double chanceRoll = Math.random();
        //System.out.println(getTiles(x->x instanceof Forest && x instanceof Burnable ).toString());
        System.out.println();
        if (chanceRoll < forestFireChance){
            createForestFire();
            //System.out.println("create forest fire");
        }
        else if (chanceRoll < forestFireExtinquishChance){
            extinquishForestFire();
            //System.out.println("extinquish forest fire");
        }
    }
    private void extinquishForestFire(){
        ArrayList<Land> list = getTiles(x->x instanceof Forest && x instanceof Burnable && ((Burnable)x).isBurning());
        if(list==null|| list.size()==0){
            //System.out.println("no forests to extinquish");
            return;
        }
        ((Forest)list.get((int)(Math.random()*list.size()))).swithStrategy(new Growing());
    }

    private void createForestFire(){
        ArrayList<Land> list =getTiles(x->x instanceof Forest && x instanceof Burnable && !((Burnable)x).isBurning());
        if(list==null|| list.size()==0){
            //System.out.println("no forests to ignite");
            return;
        }
        ((Forest)list.get((int)(Math.random()*list.size()))).swithStrategy(new Burning());
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
        if (list.isEmpty())
        {
            return;
        }
        Land borderTile, tileToclaim;
        //int num2 =0;
        borderTile=list.get((int)(Math.random()*list.size()));
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
