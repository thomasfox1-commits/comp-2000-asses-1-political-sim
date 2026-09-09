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
}
