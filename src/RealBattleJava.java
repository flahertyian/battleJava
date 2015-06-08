import java.util.*;
import java.awt.Dimension;

public class RealBattleJava {

   public static final int WIDTH = 10;
   public static final int HEIGHT = 10;
   public static final int MAXCPULEVEL = 10;
    
    
   public static void main (String [] args){
      // System.out.println("WELCOME TO BattleJava!!!");
      Scanner user = new Scanner(System.in);
      int cpuLevel  = chooseCPULevel(user);
      int [][] pBoard= new int [HEIGHT][WIDTH];
      // 0 = no ship, 1 = ship, 2 = miss, 3 = hit
      int [][] cBoard = new int [HEIGHT][WIDTH];   
      Fleet pFleet = placePlayerShips(user, pBoard);
      Fleet cFleet = placeCPUShips(cBoard);      
      while(pFleet.floating() && cFleet.floating()){
         takePTurn(cFleet, cBoard, user);
         if(cFleet.floating()){
            int random = (int) (Math.random()*10);
            takeCTurn(pFleet, pBoard, cpuLevel, random);
         }
      }
      if(! pFleet.floating()){
         System.out.println("YOU LOST :(");
      }
      else {
         System.out.println("YOU WON!!!");
      }
     
   }
   
   public static int guessCounter(int cpuLevel){
      int counter = 0;
      for(int i = 0; i <1000; i++){
         int [][] board = new int [HEIGHT][WIDTH];
         Fleet boats = placeCPUShips(board);
         while(boats.floating()){
            int random = (int) (Math.random()*10);
            takeCTurn(boats, board, cpuLevel, random);
            counter++;
         }
         System.out.println(i);
      }
      System.out.println("Level " + cpuLevel + ": " + (counter/1000.0));
   }
      

      
   
   public static int chooseCPULevel (Scanner user) {
      System.out.println();
      System.out.println("What level would you like the computer to be? (1-" + MAXCPULEVEL + ")");
      int cpuLevel = user.nextInt();
      if(cpuLevel > 0 && cpuLevel <= MAXCPULEVEL){
         return cpuLevel;
      }
      else{
         System.out.println("Invalid level. Please try again.");
         cpuLevel = chooseCPULevel (user);
      }
      return cpuLevel;  
   }
   
   public static Fleet placePlayerShips(Scanner user, int [][] pBoard){
      Fleet pFleet =  new Fleet();
      for(int boatLength = 5; boatLength > 0; boatLength --){
         printPBoard(pBoard);
         int length = boatLength;
         if (boatLength == 1){
            length = 3;
         }
         int direction = 0;
         System.out.print("Please enter the upper left coordinate where you would like your boat (length " + length + ") to be placed: ");
            // change to account for letter+int, rather than int+int;
            //currently horizontal is 0-WIDTH, vertical is 0-HEIGHT for user
         int start = user.nextInt();
         System.out.print("Would you like the ship vertical (v) or horizontal (h)?: ");
         String orientation = user.next();
         orientation = orientation.toLowerCase();
         if(orientation.startsWith("h")){
            direction = 1;
         }
         Ship boat = new Ship (length, start/10, start%10, direction);
         if (testValidity (boat, pBoard)){
            pFleet.addShip(boat);
            for (int i = 0; i < boat.getLength(); i++){
               pBoard[boat.getCoordinate(i,0)][boat.getCoordinate(i, 1)]=1;   
            }
         }
         else {
            boatLength++;
         }
         
         if((boatLength!=length) && (boatLength!= 1)){
            System.out.println("Ship placement was not valid. Please try again");
         }
      }   
      return pFleet;
   }
   
   public static boolean testValidity(Ship boat, int [][] board){
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
   
   public static Fleet placeCPUShips (int [][] cBoard){
      Fleet cFleet =  new Fleet();
      for(int boatLength = 5; boatLength > 0; boatLength --){
         int length = boatLength;
         if (boatLength == 1){
            length = 3;
         }
         int direction = (int) (Math.random()*2);
         int row = 0;
         int col = 0;
         if (direction == 0){ 
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
  
   public static void printPBoard (int [][] board){
      System.out.println("YOUR SHIPS");
      System.out.println("  0  1  2  3  4  5  6  7  8  9 ");
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
      System.out.println("  0  1  2  3  4  5  6  7  8  9");
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
   
   public static void takeCTurn (Fleet boats, int [][] board, int cpuLevel, int random){    
      int row = (int) (Math.random()*HEIGHT);
      int col = (int) (Math.random()*WIDTH);
      int [] smartCoordinate = new int [2];
      int length = boats.getShortestBoard();
      if(cpuLevel >= 9){
         smartCoordinate = bestGuess(board, boats);
      }
      if(cpuLevel == 8){
         smartCoordinate = longestLengthStrategy (board);
      }
      if(cpuLevel == 7){
         smartCoordinate = noSurrounded(row, col, board, length);
      }
      if(cpuLevel == 6){
         smartCoordinate = noSurrounded(row, col, board, 2);
      }
      if(cpuLevel >= 6){
         row = smartCoordinate [0];
         col = smartCoordinate [1];   
      }
      if((cpuLevel >= 2 && random >= 8 )||(cpuLevel >= 3 && random >= 5)||(cpuLevel>=4 && random >= 3)||(cpuLevel>=5)){
         ArrayList<Integer> rows = new ArrayList<Integer> ();
         ArrayList<Integer> cols = new ArrayList<Integer> ();
         findHit : {
            for(Ship boat: boats.getBoats()){
               if(boat.getHits() != 0 && boat.getHits() != boat.getLength()){
                  for(int i = 0; i < boat.getLength(); i++){
                     if(board [boat.getCoordinate(i,0)][boat.getCoordinate(i,1)] == 3){
                        rows.add(boat.getCoordinate(i,0));
                        cols.add(boat.getCoordinate(i,1));
                     }
                  }
                  int rand = (int) (Math.random()*4);
                  if(rows.size() > 1){
                     if(rows.get(0) == rows.get(1)){
                        int max = -1;
                        int min = WIDTH;
                        for(int j = 0; j< cols.size(); j++){
                           if(cols.get(j)>max){
                              max = cols.get(j)*1;
                           }
                           if(cols.get(j)<min){
                              min = cols.get(j)*1;
                           }
                        }
                        System.out.print(max + "" + min);
                        if (max > min+cols.size()-1){
                           for(int i = 1; i < max-min; i++){
                              if(board[rows.get(0)][min+i] < 2){
                                 col = min+i;
                              }
                           }
                           row = rows.get(0);  
                        }
                        else { 
                           int dir = 0;
                           row = rows.get(0);
                           col = getLateHit(cols, col, rand, cpuLevel, row, board, dir);
                        }
                     }
                     else if (cols.get(0) == cols.get(1)){
                        int max = -1;
                        int min = HEIGHT;
                        for(int j = 0; j< rows.size(); j++){
                           if(rows.get(j)>max){
                              max = rows.get(j)*1;
                           }
                           if(rows.get(j)<min){
                              min = rows.get(j)*1;
                           }
                        }
                        if (max > min+rows.size()-1){
                           for(int i = 1; i < max-min; i++){
                              if(board[min+i][cols.get(0)] < 2){
                                 row = min+i;
                              }
                           }
                           col = cols.get(0);  
                        }                        
                        else { 
                           int dir = 1;
                           col = cols.get(0);
                           row = getLateHit(rows, row, rand, cpuLevel, col, board, dir);
                        }
                     }
                  }
                  
                  
                  else{
                     row = rows.get(0);
                     col = cols.get(0);
                     if(cpuLevel == 10){
                        int [] spaces = getSpaces(row, col, board);
                        int max = 0;
                        for(int i=0; i < 4; i++){
                           if(spaces[i]> max){
                              max = spaces[i];
                              rand = i;
                           }
                        }
                     }
                     if(rand==0){
                        row--;
                     }
                     else if (rand==1){
                        col++;
                     }
                     else if (rand==2){
                        row++;
                     }
                     else if (rand==3){
                        col--;
                     }
                  }  
                  break findHit;
               }
            }
         }           
      }          
      if(shotValidity(row, col, board)){
         board[row][col] += 2;
         System.out.print("Your oppenent shot at " + row + col + " ");
         if (board[row][col] == 2){
            System.out.println("BUT MISSED!");
         }
         else {
            System.out.print("AND HIT");
            boats.hit(row, col);
            System.out.println(" YOUR SHIP!");
         }
         printPBoard(board);         
      }
      else {
         takeCTurn(boats, board, cpuLevel, random);
      }     
   }
   
   public static int getLateHit(ArrayList <Integer> difs, int dif, int rand, int cpuLevel, int same, int [][] board, int dir){
      if(cpuLevel == 10){
         int min = WIDTH;
         int max = 0;
         for(Integer i: difs){
            if(i<min){
               min = i;
            }
            if(i>max){
               max = i;
            }
         }
         System.out.print(min + "" + max);
         if(dir==0){
            int [] left = getSpaces(same, min, board);
            System.out.println(left[0]+"" + left[1] + left[2] + left[3]);
            int [] right = getSpaces(same, max, board);
            System.out.println(right[0]+""+right[1] + right[2] + right[3]);
            if(left [3] >= right [1]){
               return min -1 ;
            }
            else{
               return max + 1;
            } 
         }
         if(dir==1){
            int [] top = getSpaces(min, same, board);
            System.out.print("t:"+top[0]+"" + top[1] + top[2] + top[3]);
            int [] bot = getSpaces(max, same, board);
            System.out.print("r:"+bot[0]+"" + bot[1] + bot[2] + bot[3]);
            if(top[0] >= bot[2]){
               return min-1;
            }
            else{
               return max+1;
            }
         } 
      }   
      int maxMin = difs.get(0);
      for (int i = 0; i < difs.size(); i++){
         if(rand >= 2){
            if(difs.get(i)>=maxMin){
               maxMin = difs.get(i)+1;
            }
         }
         else {
            if(difs.get(i)<=maxMin){
               maxMin = difs.get(i)-1;
            }
         }
      }
      return maxMin;
   }

   
   public static boolean shotValidity (int row, int col, int [][] board){
      if (row < HEIGHT && 0<= row && col < WIDTH && 0 <= col && board[row][col]<2){
         return true;
      }
      return false;
   }
   
   public static int [] noSurrounded(int row, int col, int [][] board, int minLength){
      int [] coordinate = new int [2];
      int [] lengths = longestSpaceAvailable(row, col, board);
      if(minLength > lengths[0]){
         coordinate [0] = -1;
         coordinate [1] = -1;
         return coordinate;
      }
      coordinate [0] = row;
      coordinate [1] = col;
      return coordinate;      
   }

   public static int [] longestSpaceAvailable(int row, int col, int [][] board){
   // returns long length, short length
      int horz = 1;
      int vert = 1;
      checkTop: {
         for(int i = row-1; i >= 0; i--){
            if(board[i][col] <= 1){
               vert++;
            }
            else {
               break checkTop;
            }        
         }
      }
      checkBottom: {
         for(int i = row+1; i < HEIGHT; i++){
            if(board[i][col] <= 1){
               vert++;
            }
            else {
               break checkBottom;
            }        
         }
      }
      checkLeft: {
         for(int i = col-1; i >= 0; i--){
            if(board[row][i] <= 1){
               horz++;
            }
            else {
               break checkLeft;
            }        
         }
      }
      checkRight: {
         for(int i = col+1; i < WIDTH; i++){
            if(board[row][i] <= 1){
               horz++;
            }
            else {
               break checkRight;
            }        
         }
      }
      int [] lengths = new int [2];
      if(horz <= vert){
         lengths [0] = vert;
         lengths [1] = horz;
      }
      else{
         lengths [0] = horz;
         lengths [1] = vert;
      }
      return lengths;
   }
   
   public static int [] longestLengthStrategy(int [][] board){
      int [] coordinates = new int [2];
      int max = 1;
      for(int i = 0; i < HEIGHT; i++){
         for(int j = 0; j < WIDTH; j++){
            int [] lengths = longestSpaceAvailable(i,j, board); 
            int rand = (int) (Math.random()*4);
            if ((lengths [0] + lengths [1] > max || (lengths[0]+lengths[1] == max && rand == 0))&& board[i][j] <= 1){
               //arbitrary 1/4 chance of selecting if the same - as to not guess same pattern every time
               max = lengths[0] + lengths[1];
               coordinates [0] = i;
               coordinates [1] = j;
            }  
         }
      }
      return coordinates;                
   }
   
   public static int [] bestGuess (int [][]board, Fleet boats) {
      ArrayList <Integer> lengths = boats.getAllLengths();
      //not actually using info that wouldn't be available to player guesser
      int max = 0;
      int [] coordinates = new int [2];
      for(int i = 0; i< HEIGHT; i++){
         for (int j = 0; j < WIDTH; j++){
            int randy = (int) (Math.random()*2);
            if (board [i][j] < 2){
               int possibles = getNumPossible(lengths, board, i, j);
               //System.out.print(possibles + " ");
               //test
               if (possibles > max || possibles == max && randy == 0 ){
                  max = possibles;
                  coordinates [0] = i;
                  coordinates [1] = j;
               }
            }
         }
         //System.out.println();
         //test
      }  
      return coordinates;
   }
   
   public static int getNumPossible (ArrayList <Integer> lengths, int [][] board,int row,int col){
      int poss = 0;
      int [] spacesAvailable = getSpaces(row, col, board);
      //System.out.println(spacesAvailable[0] + " " + spacesAvailable[1] + " " + spacesAvailable[2] + " " + spacesAvailable[3]);
      //test
      for(Integer length : lengths){
         int [] space = new int [4];
         for(int i = 0; i <4; i++){
            space [i] = spacesAvailable[i];
            if(space[i] > length-1){
               space [i] = length -1;
            }
         }
         for(int i = length; i<= space[0]+ space[2] +1; i++){  
            poss++;
         }
         for(int i = length; i<= space[1] + space[3]+1; i++){  
            poss++;
         }
      } 
      return poss; 
   }
         
   public static int [] getSpaces (int row,int col,int [][] board){
      int [] spaces = new int [4];
      checkT: {
         for(int i = row-1; i >= 0; i--){
            if(board[i][col] <= 1){
               spaces [0] ++;
            }
            else {
               break checkT;
            }        
         }
      }
      checkB: {
         for(int i = row+1; i < HEIGHT; i++){
            if(board[i][col] <= 1){
               spaces [2] ++;
            }
            else {
               break checkB;
            }        
         }
      }
      checkL: {
         for(int i = col-1; i >= 0; i--){
            if(board[row][i] <= 1){
               spaces[3]++;
            }
            else {
               break checkL;
            }        
         }
      }
      checkR: {
         for(int i = col+1; i < WIDTH; i++){
            if(board[row][i] <= 1){
               spaces[1]++;
            }
            else {
               break checkR;
            }        
         }
      }
      return spaces;
   }
   
   public boolean isHit (Dimension crd, int [][] board){
      //untested - but should work ... other methods have been returning the board, even if not set as the return
      board[(int)crd.getHeight()][(int)crd.getWidth()] += 2;
      if(board[(int)crd.getHeight()][(int)crd.getWidth()]==3){
         return true;
      }
      return false;
   }
                
}