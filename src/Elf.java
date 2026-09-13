import java.awt.Color;
import java.util.ArrayList;

public class Elf extends Empire{
    public Elf(Color col, int ID){
        ownedLand= new ArrayList<>();
        improvables = new ArrayList<>();
        allies = new ArrayList<>();
        nationColor = col;
        troops = 1;
        this.ID = ID;
    }
}
