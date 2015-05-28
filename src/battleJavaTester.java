import java.util.*;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
public class battleJavaTester {
   public static void main (String [] args){
      JFrame frame = new JFrame();
      frame.setLayout(new GridLayout(10, 10));
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BattleJava!!!");
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      OceanPanel panel = new OceanPanel(10, 10);
      panel.setSize( 400, 800 );
      frame.add(panel);
   }
}
