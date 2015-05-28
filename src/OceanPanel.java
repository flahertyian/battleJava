import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event;

public class OceanPanel extends JPanel implements ActionListener{
    private int sizeX;
    private int sizeY;
    private BufferedImage topIm;

  public OceanPanel(int sizeX, int sizeY){
    this.sizeY = sizeY;
    this.sizeX = sizeX;
    topIm = new BufferedImage(sizeX,sizeY);
    Graphics2D g2dTop = topIm.createGraphics();
    drawBoard(g2dTop);
  }

  private void drawBoard(Graphics2D g2d){


    // g2d.setColor(Color.BLACK);
    // g2d.fillRect(0,0,sizeX,sizeX);
    // g2d.setColor(Color.BLUE);
    // for(int r = 0; r < sizeX; r ++){
    //   for(int c = 0; c < sizeY; c++) {

        //the drawing of the cubes to the image

    //   }
    // }
  }
  private void actionPreformed(ActionEvent event){



  }


}
