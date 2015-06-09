import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
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
   int[][] enemyOcean;
   Point[][] enemyBoard;
   Fleet boats;
   int HEIGHT = 10;
   int WIDTH = 10;
   PlayerPanel pPanel;

   

   public OceanPanel(int sizeX, int sizeY, Fleet boats, PlayerPanel pPanel, int [][] enemyOcean){
      this.sizeY = sizeY;
      this.sizeX = sizeX;
      this.grid = new GridLayout(10,10);
      this.panel = new JPanel(grid);
      this.boats = boats;
      this.pPanel = pPanel;
      this.enemyOcean = enemyOcean;
      drawBoard();
   }
   
   public void setEnemyOcean(int[][] enemyOcean){
      this.enemyOcean = enemyOcean;
   }

   private void drawBoard(){
   
      makeButtonArr();
      for(int i = 0; i<sizeY; i++){
         for(int j =0; j<sizeX; j++){
            //Point btn = new Point(j,i);
            enemyBoard[i][j].addActionListener(
                  new ActionListener(){
                     public void actionPerformed(ActionEvent e){
                        if(pPanel.isSet()){
                           Point temp =(Point)e.getSource();
                           Dimension dimension = temp.getDimension();
                           takePTurn (boats, enemyOcean, (int)dimension.getHeight(), (int)dimension.getWidth(), temp, enemyBoard);
                        }      
                     }
                  });
            enemyBoard[i][j].setPreferredSize(new Dimension(20, 20));
            panel.add(enemyBoard[i][j]);
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
   
    public void takePTurn (Fleet boats, int [][] board, int row, int col, Point temp, Point [][] enemyBoard){
         int random = (int)(Math.random()*10);
         if(shotValidity(row, col, board)){
         board[row][col] += 2;
         if (board[row][col] == 2){
            temp.setMiss();
            pPanel.takeCTurn(random);
            //System.out.println("MISS!");
         }
         else if (board[row][col] == 3) {
            temp.setHit();
            //System.out.print("HIT");
            boats.hit(row, col, enemyBoard);
            pPanel.takeCTurn(random);
            
            //System.out.println("!");
         }  
      }
      //else{
         //System.out.println("Invalid shot. Try again.");
         //takePTurn(boats, board, user);
         //look at
      //}
   }
   
   public static boolean shotValidity (int row, int col, int [][] board){
      if (row < 10 && 0<= row && col < 10 && 0 <= col && board[row][col]<2){
         return true;
      }
      return false;
   }



   public JPanel getPanel(){
      return this.panel;
   }
   
   public Point[][] getBoard(){
      return enemyBoard;
   }
   
   private void makeButtonArr(){
      enemyBoard = new Point[sizeX][sizeY];
      for(int y = 0; y<sizeY; y++){
         for(int x =0; x<sizeX; x++){
            enemyBoard[x][y] = new Point(x,y);
         }
      }
   }
   



}
