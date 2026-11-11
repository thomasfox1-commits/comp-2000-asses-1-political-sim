import java.awt.Color;
public class Growing implements Growth {
    private Color statusColor = new Color(31,80,44);
    public Growing(){

    }

    public Double addGrowth(Double value){
        return value+=0.4;
    }

    public Color getStatusColor(){
        return statusColor;
    }
}
