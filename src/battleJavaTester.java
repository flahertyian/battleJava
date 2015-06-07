import java.util.*;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class battleJavaTester {
   public static void main (String [] args){
      JFrame frame = new JFrame();

      //frame.setLayout(new GridLayout(10, 10));
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BattleJava!!!");
      frame.setLocationRelativeTo(null);

      OceanPanel panel1 = new OceanPanel(10, 10);
      int[][] enemyOcean = new int[10][10];
      for(int i = 0; i < 9; i++){
         enemyOcean [i][i] = 1;
         enemyOcean[i+1][i] = 1;
      }
      panel1.setEnemyOcean(enemyOcean);
      PlayerPanel panel2 = new PlayerPanel(10, 10);
      //panel.setSize( 400,800 );
      //panel.setMinimumSize(panel.getPreferredSize());

      JPanel box = new JPanel();

      box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
      box.add(panel1.getPanel());
      box.add(panel2.getPanel());
      frame.add(box);

      frame.setVisible(true);
   }
}
