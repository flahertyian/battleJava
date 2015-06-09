import java.util.*;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class battleJavaTester {

   public final static int HEIGHT = 10;
   public final static int WIDTH = 10;

   int[][] cBoard;
   int[][] pBoard;

   Fleet cpuBoats;
  //BufferedImage winScreen;
  //BufferedImage loseScreen;

   public battleJavaTester(){
      Scanner user = new Scanner(System.in);
      int cpuLevel = chooseCPULevel(user);
      this.cBoard = new int[10][10];
         //this.pBoard = new int[10][10];
      JFrame frame = new JFrame();
         //frame.setLayout(new GridLayout(10, 10));
      frame.setSize(800,800);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BattleJava!!!");
      frame.setLocationRelativeTo(null);
      this.cpuBoats = placeCPUShips(cBoard);
         
      PlayerPanel panel2 = new PlayerPanel(10, 10, cpuLevel);
         //set cpuLevel
      OceanPanel panel1 = new OceanPanel(10, 10, cpuBoats, panel2, cBoard);
         //panel1.setEnemyOcean(cBoard);  
         //panel.setSize( 400,800 );
         //panel.setMinimumSize(panel.getPreferredSize());
      JPanel box = new JPanel();
      box.setLayout(new BoxLayout(box,BoxLayout.Y_AXIS));
      box.add(panel1.getPanel());
      box.add(panel2.getPanel());
      frame.add(box);
      frame.setVisible(true);
   }

   // public JPanel winScreen(){
//       JPanel end = new JPanel();
//       try {
//           image = ImageIO.read(new File("../resource/battleJavaMurica.png"));
//        } catch (IOException ex) {
//          ex.printStackTrace();
//        }
// 
//    }

   public Fleet placeCPUShips (int [][] cBoard){
      //genirates new fleet
      Fleet cFleet =  new Fleet();
      //loops through the length of the boat
      for(int boatLength = 5; boatLength > 0; boatLength --){
         int length = boatLength;
         if (boatLength == 1){
            length = 3;
         }
         int direction = (int) (Math.random()*2)+1;
         int row = 0;
         int col = 0;
         if (direction == 1){
            row = (int) (Math.random()*(HEIGHT-boatLength));
            col = (int) (Math.random()*WIDTH);
         }
         else {
            row = (int) (Math.random()*HEIGHT);
            col = (int) (Math.random()*(WIDTH-boatLength));
         }
         Ship boat = new Ship (length, row, col, direction);
         if (testValidity (boat, cBoard)){
            cFleet.addShip(boat);
            for (int i = 0; i < boat.getLength(); i++){
               cBoard[boat.getCoordinate(i,0)][boat.getCoordinate(i,1)]=1;
            }
         }
         else {
            boatLength++;
         }
      }
      return cFleet;
   }

   public boolean testValidity(Ship boat,int[][] board){
      for (int i = 0; i < boat.getLength(); i++){
         if (boat.getCoordinate(i, 0) >= HEIGHT || boat.getCoordinate(i,1) >= WIDTH){
            return false;
         }
         if (board[boat.getCoordinate(i,0)][boat.getCoordinate(i,1)]==1){
            return false;
         }
      }
      return true;
   }

   public static void main (String [] args){
      battleJavaTester runner = new battleJavaTester();
   }
   
   public static int chooseCPULevel (Scanner user) {
      System.out.println();
      System.out.println("What level would you like the computer to be? (1-10)");
      int cpuLevel = user.nextInt();
      if(cpuLevel > 0 && cpuLevel <= 10){
         return cpuLevel;
      }
      else{
         System.out.println("Invalid level. Please try again.");
         cpuLevel = chooseCPULevel (user);
      }
      return cpuLevel;  
   }
}




