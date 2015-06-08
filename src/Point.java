import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;

//worst name for a JButton ever lol my b 0_0
public class Point extends JButton{
  int x;
  int y;
  ImageIcon hit;
  ImageIcon miss;
  ImageIcon ship;
  ImageIcon background;
  Dimension dimension;

  public Point(int x, int y){
    background = new ImageIcon("../resource/battleJavaBase.gif");
    super.setIcon(background);
    this.x = x;
    this.y = y;
    ship = new ImageIcon("../resource/battleJavaShip.gif");
    hit = new ImageIcon("../resource/battleJavaHit.gif");
    miss = new ImageIcon("../resource/battleJavaMiss.gif");
    dimension = new Dimension(x,y);

  }

  public void setHit(){
    super.setIcon(hit);
  }
  public void setMiss(){
    super.setIcon(miss);
  }
  public void setBackground(){
    super.setIcon(background);
  }

  public void setShip(){
    super.setIcon(ship);
  }

  public Dimension getDimension(){
    return dimension;
  }

}
