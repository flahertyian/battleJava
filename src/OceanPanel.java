import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

public class OceanPanel{
   private int sizeX;
   private int sizeY;
   GridLayout grid;
   JPanel panel;
   Icon hit;
   Icon miss;

   public OceanPanel(int sizeX, int sizeY){
      this.sizeY = sizeY;
      this.sizeX = sizeX;
      this.grid = new GridLayout(10,10);
      this.panel = new JPanel(grid);
      hit = new ImageIcon("../resource/battleJavaHit.gif");
      miss = new ImageIcon("../resource/battleJavaMiss.gif");
      drawBoard();
   }

   private void drawBoard(){
      Icon background = new ImageIcon("../resource/battleJavaBase.gif");
      // if(background!=null){
      //   System.out.print("battleJavaBase was reached");
      // }

      for(int i = 0; i<sizeY; i++){
         for(int j =0; j<sizeX; j++){
            JButton btn = new JButton(background);
            btn.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e){
                System.out.println(System.currentTimeMillis());
                //if(is ship){
                  JButton temp =(JButton)e.getSource();
                  temp.setIcon(hit);
                //}else{
                  //e.getSource.setIcon(miss);
                //}
              }
            });
            btn.setPreferredSize(new Dimension(20, 20));
            panel.add(btn);
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

   public JPanel getPanel(){
      return this.panel;
   }


}
