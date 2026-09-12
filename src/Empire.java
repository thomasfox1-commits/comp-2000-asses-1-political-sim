import java.awt.*;
import java.util.*;
public class Empire {
    ArrayList<Land> ownedLand;
    ArrayList<Land> improvables;
    Color nationColor;
    int troops;
    int ID;

    public Empire(){
        ownedLand= new ArrayList<>();
        improvables = new ArrayList<>();
        nationColor = Color.white;
        troops = 1;
        this.ID = 0;
    }

    public Empire(Color col, int ID){
        ownedLand= new ArrayList<>();
        improvables = new ArrayList<>();
        nationColor = col;
        troops = 1;
        this.ID = ID;
    }

    public ArrayList<Land> getBorderTiles(){
        ArrayList<Land> list= new ArrayList<>();
        if (ownedLand==null){
            return null;
        }
        for(Land l:ownedLand){
            for(Land x: l.getAdjTiles()){
                if(!isOwner(x)){
                    list.add(l);
                    break;
                }
            }
        }
        return list;
    }
    public ArrayList<Land> getPlayableBorderTiles(){
        ArrayList<Land> list= new ArrayList<>();
        if (ownedLand==null){
            return null;
        }
        for(Land l:ownedLand){
            for(Land x: l.getAdjTiles()){
                if(!isOwner(x)&&isClaimable(l)){
                    list.add(l);
                    break;
                }
            }
        }
        return list;
    }
    public void addTile(Land tile){
        ownedLand.add(tile);

        if (tile instanceof Improvable){
            improvables.add(tile);
        }

        if (tile.owner != null)
        {
            tile.owner.ownedLand.remove(tile);
            if(tile.owner.improvables.contains(tile))
            {
                tile.owner.improvables.remove(tile);
            }
        }

        tile.owner = this;
    }
    public boolean isClaimable(Land tile){
        //TODO
        if(tile ==null){
            return false;
        }
        return true;
    }
    public Boolean isOwner(Land tile){
        if(tile ==null){
            return true;
        }
        return ownedLand.contains(tile);
    }
    public void updateTiles(){
        for(Land l:ownedLand){
            l.setBackground(nationColor);
        }
    }
    public void improveTile()
    {
        Land l = improvables.get((int)Math.floor(Math.random() * improvables.size()));
        if (l instanceof Improvable tile && tile.getBonus() < 1)
        {
            l.setBackground(Color.YELLOW);
            tile.levelUp();
        }
        else
        {
            l.setBackground(Color.ORANGE);
            troops++;
            improvables.remove(l);
        }
    }
    public int getImprovableCount()
    {
        return improvables.size();
    }
    public int getTroops()
    {
        return troops;
    }
    public int getTileCount()
    {
        return ownedLand.size();
    }
    public int getID()
    {
        return ID;
    }
}
