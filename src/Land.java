import javax.swing.*;
abstract class  Land<Tile extends Land>  extends JPanel  {
    int ID;
    Tile left,right,above,below;
    public Land(){
        ID=0;
        left=null;
        right=null;
        above=null;
        below=null;
    }
    public Land(int identifier){
        ID=identifier;
        left=null;
        right=null;
        above=null;
        below=null;
    }
    public void setRight(Tile land){
        right=land;
    }
    public void setLeft(Tile land){
        left=land;
    }
    public void setAbove(Tile land){
        above=land;
    }
    public void setBelow(Tile land){
        right=land;
    }
}
