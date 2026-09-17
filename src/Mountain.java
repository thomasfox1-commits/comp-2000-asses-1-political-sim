import java.awt.Dimension;
import java.awt.Graphics;

public class Mountain extends Land implements Impassable {

    int[] xPoints = {10, 35, 50, 60, 90};
    int[] yPoints = {95, 15, 50, 10, 95};
    @Override
    public void drawLand() {
        // TODO Auto-generated method stub
        super.drawLand();
        int width = getSize().width;
        int height = getSize().height;
        for (int i : xPoints) {
            xPoints[i] *= width;
            xPoints[i] /= 100;
            yPoints[i] *= height;
            yPoints[i] /= 100;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.drawLine(0,0,20,20);
    }

}
