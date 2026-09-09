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
            
        }
        return list;
    }

    public Boolean isOwner(Land tile){
        if (ownedLand==null){
            return false;
        }
        for(Land l:ownedLand){
            if(l==tile){
                return true;
            }
        }
        return false;
    }
}
