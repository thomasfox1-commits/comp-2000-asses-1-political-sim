import java.awt.Color;
import java.util.ArrayList;

public class Human extends Empire {
    public Human(Color col, int ID){
        ownedLand= new ArrayList<>();
        improvables = new ArrayList<>();
        allies = new ArrayList<>();
        nationColor = col;
        troops = 1;
        this.ID = ID;
    }
}
