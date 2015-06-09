import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;

//worst name for a JButton ever lol my b 0_0
public class Point extends JButton{
  private int x;
  private int y;
  private ImageIcon hit;
  private ImageIcon miss;
  private ImageIcon ship;
  private ImageIcon sunk;
  private ImageIcon background;
  private ImageIcon invalid;
  private Dimension dimension;
  private boolean isShip;


  public Point(int x, int y){
    background = new ImageIcon("../resource/battleJavaBase.gif");
    super.setIcon(background);
    this.x = x;
    this.y = y;
    ship = new ImageIcon("../resource/battleJavaShip.gif");
    hit = new ImageIcon("../resource/battleJavaHit.gif");
    miss = new ImageIcon("../resource/battleJavaMiss.gif");
    sunk = new ImageIcon("../resource/battleJavaSunk.gif");
    invalid = new ImageIcon("../resource/battleJavaInvalid.gif");
    dimension = new Dimension(x,y);
    isShip = false;
  }

  //sets the icon of the Point to a hit
  public void setHit(){
    super.setIcon(hit);
  }

  //sets the icon of the point to a miss
  public void setMiss(){
    super.setIcon(miss);
  }

  //sets the icon of the point to the original color
  public void setBackground(){
    super.setIcon(background);
  }

  //both sets the icon to the color of a ship but also sets an inner boolean to true;
  public void setShip(){
    super.setIcon(ship);
    isShip = true;
  }

  //returns weather the point is a Ship or not
  public boolean isShip(){
   return isShip;
  }

  //returns the dimension inside the pannel of the point
  public Dimension getDimension(){
    return dimension;
  }

  //sets the icon for this spicific JButton to a sunk
  public void setSunk(){
    super.setIcon(sunk);
  }

  //sets the icon to a invalid marker
  public void setInvalid(){
    super.setIcon(invalid);
  }

}
