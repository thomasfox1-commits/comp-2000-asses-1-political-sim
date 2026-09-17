import java.awt.Graphics;

public class Forest extends Land implements Improvable{
    private double growthBonus;
    int[] xPoints = {5, 30, 50, 70, 95};
    int[] yPoints = {75, 15, 60, 15, 75};
    int[] firstRect = {25, 100};
    int[] secondRect = {65, 100};
    int[] rectSize = {10, 25};
    
    public Forest(){
        growthBonus = 0.2;
    }
    
    public void levelUp(){
        growthBonus += 0.4;
    }

    public double getBonus(){
        return growthBonus;
    }

    @Override
    public void drawLand(int width, int height) {
        // TODO Auto-generated method stub
        super.drawLand(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
    }
}
