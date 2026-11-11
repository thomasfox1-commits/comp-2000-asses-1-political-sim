import java.awt.Color;
public class Burning implements Growth {
    private Color statusColor=Color.RED;
    public Double addGrowth(Double value){
        return Math.max(0.2,value-=0.4);
    }
    public Color getStatusColor(){
        return statusColor;
    }
}