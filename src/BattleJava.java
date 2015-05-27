import java.util.*;
import javax.swing.JFrame;

public class BattleJava {

   public static final int WIDTH = 8;
   public static final int HEIGHT = 8;

   public static void main (String [] args){

      JFrame frame = new JFrame();
      frame.setSize(400,800);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BattleJava!!!");
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);

      /**
         if(all ships are entered and happy){
            add Jpanel to JFrame
            make Frame visibale
         }
         */
      System.out.println("WELCOME TO BattleJava!!!");
      Scanner user = new Scanner(System.in);
      int [][] pBoard= new int [HEIGHT][WIDTH];
      // 0 = no ship, 1 = ship, 2 = miss, 3 = hit
      int [][] cBoard = new int [HEIGHT][WIDTH];
      Fleet pFleet = placePlayerShips(user, pBoard);
      Fleet cFleet = placeCPUShips(cBoard);
      while(pFleet.floating() && cFleet.floating()){
         takePTurn(cFleet, cBoard, user);
         if(cFleet.floating()){
            takeCTurn(pFleet, pBoard);
         }
      }
      if(! pFleet.floating()){
         System.out.println("YOU LOST :(");
      }
      else {
         System.out.println("YOU WON!!!");
      }

   }

   public static Fleet placePlayerShips(Scanner user, int [][] pBoard){
      Fleet pFleet =  new Fleet();
      for(int boatlength = 5; boatlength > 0; boatlength --){
         printPBoard(pBoard);
         int length = boatlength;
         if (boatlength == 1){
            length = 3;
         }
         int direction = 0;
         System.out.print("Please enter the upper left coordinate where you would like your boat (length " + length + ") to be placed: ");
            // change to account for letter+int, rather than int+int;
            //currently horizontal is 0-WIDTH, vertical is 0-height for user
         int start = user.nextInt();
         System.out.print("Would you like the ship vertical (v) or horizontal (h)?: ");
         String orientation = user.next();
         orientation = orientation.toLowerCase();
         if(orientation.startsWith("h")){
            direction = 1;
         }
         Ship boat = new Ship(length, start/10, start%10, direction);
         if(testValidity(boat,pBoard)){
            pFleet.boats.add(boat);
            changeBoard(boat, pBoard);
         }
         else {
            System.out.println("Ship placement was not valid. Please try again.");
            boatlength++;
         }
      }
      return pFleet;
   }

   public static boolean testValidity(Ship boat, int [][] board){
      for (int i = 0; i < boat.length; i++){
         if (boat.coordinates[i][0] >= HEIGHT || boat.coordinates [i][1] >= WIDTH){
            return false;
         }
         if (board[boat.coordinates[i][0]][boat.coordinates[i][1]]==1){
            return false;
         }
      }
      return true;
   }

   public static void changeBoard(Ship boat, int [][] board){
      for (int i = 0; i < boat.length; i++){
         board[boat.coordinates[i][0]][boat.coordinates[i][1]]=1;
      }
   }

   public static Fleet placeCPUShips (int [][] cBoard){
      Fleet cFleet =  new Fleet();
      for(int boatlength = 5; boatlength > 0; boatlength --){
         int length = boatlength;
         if (boatlength == 1){
            length = 3;
         }
         int direction = (int) (Math.random()*2);
         int row = 0;
         int col = 0;
         if (direction == 0){
            row = (int) (Math.random()*(HEIGHT-boatlength));
            col = (int) (Math.random()*WIDTH);
         }
         else {
            row = (int) (Math.random()*HEIGHT);
            col = (int) (Math.random()*(WIDTH-boatlength));
         }
         Ship boat = new Ship(length, row, col, direction);
         if(testValidity(boat,cBoard)){
            cFleet.boats.add(boat);
            changeBoard(boat, cBoard);
         }
         else {
            boatlength++;
         }
      }
      return cFleet;
   }

   public static void printPBoard (int [][] board){
      System.out.println("YOUR SHIPS");
      System.out.println("  0  1  2  3  4  5  6  7 ");
      for(int row = 0; row< HEIGHT; row++){
         System.out.print(row);
            //Change to number
         for (int col = 0; col <WIDTH; col++){
            System.out.print(" ");
            if(board[row][col] == 0){
               System.out.print(" ");
            }
            else if(board[row][col] == 1){
               System.out.print("S");
            }
            else if(board[row][col] == 2){
               System.out.print("o");
            }
            else if(board[row][col] == 3){
               System.out.print("X");
            }
            System.out.print(" ");
         }
         System.out.println();
      }
      System.out.println();
   }

   public static void printCBoard (int [][] board){
      System.out.println("OPPONENT'S SHIPS");
      System.out.println("  0  1  2  3  4  5  6  7 ");
      for(int row = 0; row< HEIGHT; row++){
         System.out.print(row);
            //Change to letter
         for (int col = 0; col <WIDTH; col++){
            System.out.print(" ");
            if(board[row][col] == 0){
               System.out.print(" ");
            }
            else if(board[row][col] == 1){
               System.out.print(" ");
                  //can make visible for testability
            }
            else if(board[row][col] == 2){
               System.out.print("o");
            }
            else if(board[row][col] == 3){
               System.out.print("X");
            }
            System.out.print(" ");
         }
         System.out.println();
      }
      System.out.println();
   }

   public static void takePTurn (Fleet boats, int [][] board, Scanner user){
      System.out.print("Your turn:");
      printCBoard(board);
      System.out.println("Enter a coordinate to shoot at: ");
      int target = user.nextInt();
         //change to account for E5, rather than 45)
      int row = target/10;
      int col = target%10;
      if(shotValidity(row, col, board)){
         board[row][col] += 2;
         if (board[row][col] == 2){
            System.out.println("MISS!");
         }
         else {
            System.out.print("HIT");
            boats.hit(row, col);
            System.out.println("!");
         }
         printCBoard(board);
      }
      else{
         System.out.println("Invalid shot. Try again.");
         takePTurn(boats, board, user);
      }
   }

   public static void takeCTurn (Fleet boats, int [][] board){
      int row = (int) (Math.random()*HEIGHT);
      int col = (int) (Math.random()*WIDTH);
      if(shotValidity(row, col, board)){
         board[row][col] += 2;
         System.out.print("Your oppenent shot at " + row + col + " ");
         if (board[row][col] == 2){
            System.out.println("BUT MISSED!");
         }
         else {
            System.out.println("AND HIT");
            boats.hit(row, col);
            System.out.println(" YOUR SHIP!");
         }
         printPBoard(board);
      }
      else {
         takeCTurn(boats, board);
      }
   }

   public static boolean shotValidity (int row, int col, int [][] board){
      if (row < HEIGHT && col < WIDTH && board[row][col]<2){
         return true;
      }
      return false;
   }

   // @Override
   // private void paintComponent(Graphics g){
   //    super.paintComponent(g);

   // }
}
