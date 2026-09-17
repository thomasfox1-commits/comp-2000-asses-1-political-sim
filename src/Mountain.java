import java.awt.Dimension;
import java.awt.Graphics;

public class Mountain extends Land implements Impassable {

    int[] xPoints = {10, 35, 50, 60, 90};
    int[] yPoints = {95, 15, 50, 10, 95};
    @Override
    public void drawLand(int width, int height) {
        // TODO Auto-generated method stub
        super.drawLand(width, height);
        for(int i = 0; i < xPoints.length; i++){
            xPoints[i] *= width;
            xPoints[i] /= 100;
            yPoints[i] *= height;
            yPoints[i] /= 100;
        }
        System.out.println(xPoints[0] + ", " + yPoints[0]);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.drawPolyline(xPoints, yPoints, xPoints.length);
    }

}
