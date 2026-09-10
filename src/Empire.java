import java.awt.*;
import java.util.*;
public class Empire {
    ArrayList<Land> ownedLand;
    Color nationColor;
    public Empire(){
        ownedLand= new ArrayList<>();
        nationColor = Color.white;
    }

    public Empire(Color col){
        ownedLand= new ArrayList<>();
        nationColor = col;
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
    }
    public boolean isClaimable(Land tile){
        //TODO
        if(tile ==null){
            return false;
        }
        return true;
    }
    public Boolean isOwner(Land tile){
        if (ownedLand==null){
            return false;
        }
        return ownedLand.contains(tile);
    }
    public void updateTiles(){
        for(Land l:ownedLand){
            l.setBackground(nationColor);
        }
    }
}
