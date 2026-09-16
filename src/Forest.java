public class Forest extends Land implements Improvable{
    private double growthBonus;
    
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
    public void drawLand() {
        // TODO Auto-generated method stub
        super.drawLand();
    }
}
