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
        factions = new ArrayList<>();
        this.gridSize = gridSize;
        setSize(width,height);
        setLayout(null);;
        //System.out.println("grid tile Width: " + tileSizeWidth + " Height: " + tileSizeHeight);
        createGrid();
        initalizePlayers(4);
    }

    private void initalizePlayers(int playerAmount){
        int raceCount=2;
        for(int i=0;i<playerAmount;i++){
            addPlayer((int)(Math.random()*raceCount),Color.CYAN,i);

        }

    }

    private void createGrid(){
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
                }
                else if (terrainType >= 0.9){
                    placeHolder=new Mountain();
                    placeHolder.setBackground(Color.gray);
                }
                else
                {
                    placeHolder=new Plains();
                    placeHolder.setBackground(Color.black);
                }
                placeHolder.ID=tileID;
                
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
    }

    public void addPlayer(int people,Color factionCol, int ID){
        Empire player;
        Land firstLand=null;

        switch (people) {
            case 1:
                player= new Human(factionCol,ID); 
                break;
            case 2:
                player=new Elf(factionCol,ID);
                break;
            default:
                player=new Empire(factionCol,ID);
                break;
        }
    
        while(!player.isClaimable(firstLand) ||findOwner(firstLand)!=null){
            firstLand=getTile((int)(Math.random()*gameMap.size()));
        }

        player.addTile(firstLand);
        factions.add(player);
        player.updateTiles();
    }

    public Empire findOwner(Land tile){
        for(Empire e: factions){
            if(e.isOwner(tile)&&tile!=null){
                return e;
            }
        }
        return null;
    }

    public void updateTurn(){
        for (int i = 0; i < 20; i++)
        {
            System.out.println();
        }

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
                        try{
                            claimRandomTile(faction);
                            faction.updateTiles();
                        }
                        catch(Exception e){
                            System.out.println("error occured when taking tile ");
                        }
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

            System.out.println("Faction " + faction.getID() + " Troops: " + faction.getTroops());
            System.out.println("Faction " + faction.getID() + " Total Tiles: " + faction.getTileCount());
            System.out.println("Faction " + faction.getID() + " Improvable Tiles: " + faction.getImprovableCount());
            for (int j = 0; j < faction.getAllies().size(); j++)
            {
                if (factions.contains(faction.getAllies().get(j)))
                {
                    System.out.println("Faction " + faction.getID() + " Allied With " + faction.getAllies().get(j).getID());
                }
            }
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
    public void claimRandomTile(Empire faction)throws Exception{
        ArrayList<Land> list = faction.getPlayableBorderTiles();
        Land borderTile, tileToclaim;
        if (list.isEmpty())
        {
            return;
        }

        borderTile=list.get((int)(Math.random()*list.size()));
        list=borderTile.getAdjTiles();
        int num=(int)(Math.random()*list.size());
        tileToclaim=null;

        while(faction.isOwner(tileToclaim)||!faction.isClaimable(tileToclaim)){
            num=(int)(Math.random()*list.size());
            tileToclaim=list.get(num);
        }

        faction.addTile(tileToclaim);
    }
}
