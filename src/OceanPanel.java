import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OceanPanel extends JPanel implements ActionListener{
   private int sizeX;
   private int sizeY;
  
   public OceanPanel(int sizeX, int sizeY){
      this.sizeY = sizeY;
      this.sizeX = sizeX;
      drawBoard();
   }

   private void drawBoard(){
       for(int i = 0; i<sizeY; i++){
          for(int j =0; j<sizeX; j++){
            super.add(new JButton("1"));
          }
       }
      // g2d.setColor(Color.BLACK);
    // g2d.fillRect(0,0,sizeX,sizeX);
    // g2d.setColor(Color.BLUE);
    // for(int r = 0; r < sizeX; r ++){
    //   for(int c = 0; c < sizeY; c++) {
   
        //the drawing of the cubes to the image
   
    //   }
    // }
   }
   public void actionPerformed(ActionEvent event){
   
   
   
   }


}
