import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

public class PlayerPanel{
   private int sizeX;
   private int sizeY;
   private GridLayout grid;
   private JPanel panel;
   private Icon hit;
   private Icon miss;
   private boolean second;
   private Dimension curHeadDim;
   private Dimension curTailDim;
   private Point[][] playerBoard;
   private int[][] board;
   private Fleet boats;
   private int cpuLevel;

   public PlayerPanel(int sizeX, int sizeY, int cpuLevel){
      this.sizeY = sizeY;
      this.sizeX = sizeX;
      this.grid = new GridLayout(10,10);
      this.panel = new JPanel(grid);
      second = false;
      this.board = new int [10][10];
      this.boats = new Fleet();
      this.cpuLevel = cpuLevel;

      makeButtonArr();
      drawBoard();

   }

   private void makeButtonArr(){
      playerBoard = new Point[sizeX][sizeY];
      for(int x = 0; x<sizeY; x++){
         for(int y =0; y<sizeX; y++){
            playerBoard[x][y] = new Point(x,y);
         }
      }
   }

   private void drawBoard(){


      for(int i = 0; i<sizeY; i++){
         for(int j =0; j<sizeX; j++){
            playerBoard[i][j].addActionListener(
                  new ActionListener(){
                     public void actionPerformed(ActionEvent e){

                        if(second){ //if this is the second click
                           Point temp =(Point)e.getSource();
                           curTailDim = temp.getDimension();

                        // 0 no boat, 1 boat, 2 miss, 3 hit
                           if(curHeadDim.getWidth() == curTailDim.getWidth()||
                           curHeadDim.getHeight() == curTailDim.getHeight()){
                              int xDif = (int)Math.abs((Double)(curHeadDim.getWidth() - curTailDim.getWidth()));
                              int yDif = (int)Math.abs((Double)(curHeadDim.getHeight() - curTailDim.getHeight()));

                              if(curTailDim.getHeight()>curHeadDim.getHeight()){//below (y+)
                                 Ship boat = new Ship(yDif+1,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),2);
                                 if(testValidity(boat)){
                                    for(int t = 0;t<= yDif; t++){
                                       playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()+t].setShip();
                                       board[(int)curHeadDim.getHeight()+t][(int)curHeadDim.getWidth()] = 1;
                                    //System.out.print((int)curHeadDim.getHeight()+t+""+(int)curHeadDim.getWidth());
                                    }
                                    boats.addShip(boat);
                                    if(boats.isSet()){
                                    //System.out.println(" AT YA BOYZ");
                                    }

                                 }

                              }
                              else if(curTailDim.getHeight()<curHeadDim.getHeight()){ //above (y-)
                                 Ship boat = new Ship(yDif+1,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),0);
                                 if(testValidity(boat)){
                                    for(int t = 0;t<= yDif; t++){
                                       playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()-t].setShip();
                                       board[(int)curHeadDim.getHeight()-t][(int)curHeadDim.getWidth()] = 1;
                                    }
                                    boats.addShip(boat);
                                    if(boats.isSet()){
                                    //System.out.println(" AT YA BOYZ");

                                    }

                                 }
                              }
                              else if(curTailDim.getWidth()>curHeadDim.getWidth()){ // right (x+)
                                 Ship boat = new Ship(xDif+1,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),1);
                                 if(testValidity(boat)){
                                    for(int t = 0; t<= xDif; t++){
                                       playerBoard[(int)curHeadDim.getWidth()+t][(int)curHeadDim.getHeight()].setShip();
                                       board[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()+t] = 1;
                                    }
                                    boats.addShip(boat);
                                    if(boats.isSet()){
                                    //System.out.println(" AT YA BOYZ");
                                    }

                                 }
                              }
                              else if(curTailDim.getWidth()<curHeadDim.getWidth()){ //left (x-)
                                 Ship boat = new Ship(xDif+1,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),3);
                                 if(testValidity(boat)){
                                    for(int t = 0; t <= xDif; t++){
                                       playerBoard[(int)curHeadDim.getWidth()-t][(int)curHeadDim.getHeight()].setShip();
                                       board[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()-t] = 1;
                                    }
                                    boats.addShip(boat);
                                    if(boats.isSet()){
                                    //System.out.println(" AT YA BOYZ");
                                    //OceanPanel panel1 = new OceanPanel(10, 10, cpuBoats, panel2, cBoard);
                                    }
                                 }
                              }
                              second = false;
                           }
                           else{
                           //System.out.println("invalid placement");
                              second = false;
                           //if you have time set some sort of error
                           }

                        }
                        else{ // if it is the fist click (the start point of the ship)
                           Point temp = (Point)e.getSource();
                           curHeadDim = temp.getDimension();
                           second = true;
                        }
                     }});
            Point temp = playerBoard[i][j];
            temp.setPreferredSize(new Dimension(20,20));
            panel.add(temp);
         }
      }
   }
   public JPanel getPanel(){
      return panel;
   }


   private boolean testValidity(Ship boat){
   //edit to limit boat placement to right number of boats/ right length
      for (int i = 0; i < boat.getLength(); i++){
         if (boat.getCoordinate(i, 0) >= sizeY || boat.getCoordinate(i,1) >= sizeX){
            return false;
         }
         if (board[boat.getCoordinate(i,0)][boat.getCoordinate(i,1)]==1){
            //System.out.println("test vil false! ahhh");
            return false;
         }
      }
      ArrayList <Integer> lengths = boats.getAllAvailableLengths();
      for(Integer length : lengths){
         if(boat.getLength()== length){
            lengths.remove(length);
            return true;
         }
      }
      return false;
   //      return true;
   }

   public void takeCTurn(int random){
      //Fleet boats, int [][] board, int cpuLevel, int random){
      int row = (int) (Math.random()*10);
      int col = (int) (Math.random()*10);
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
                        int min = 10;
                        for(int j = 0; j< cols.size(); j++){
                           if(cols.get(j)>max){
                              max = cols.get(j)*1;
                           }
                           if(cols.get(j)<min){
                              min = cols.get(j)*1;
                           }
                        }
                        //System.out.print(max + "" + min);
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
                        int min = 10;
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
      if(shotValidity(row, col)){
         board[row][col] += 2;
         //System.out.print("Your oppenent shot at " + row + col + " ");
         if (board[row][col] == 2){
            playerBoard[col][row].setMiss();//System.out.println("BUT MISSED!");
         }
         else if (board [row][col] == 3){
            //System.out.print("AND HIT");
            playerBoard[col][row].setHit();
            boats.hit(row, col, playerBoard);
            //System.out.println(" YOUR SHIP!");
         }
         if (!boats.floating()){
<<<<<<< HEAD
            new EndOfGame(false);
            //talk to Ian
=======
             //talk to Ian
>>>>>>> 1016c16eaac725743b34ace2917048316c8d2ddc
         }
         //printPBoard(board);
      }
      else {
         takeCTurn(random);
      }
   }

   public static int getLateHit(ArrayList <Integer> difs, int dif, int rand, int cpuLevel, int same, int [][] board, int dir){
      if(cpuLevel == 10){
         int min = 10;
         int max = 0;
         for(Integer i: difs){
            if(i<min){
               min = i;
            }
            if(i>max){
               max = i;
            }
         }
         //System.out.print(min + "" + max);
         if(dir==0){
            int [] left = getSpaces(same, min, board);
            //System.out.println(left[0]+"" + left[1] + left[2] + left[3]);
            int [] right = getSpaces(same, max, board);
            //System.out.println(right[0]+""+right[1] + right[2] + right[3]);
            if(left [3] >= right [1]){
               return min -1 ;
            }
            else{
               return max + 1;
            }
         }
         if(dir==1){
            int [] top = getSpaces(min, same, board);
            //System.out.print("t:"+top[0]+"" + top[1] + top[2] + top[3]);
            int [] bot = getSpaces(max, same, board);
            //System.out.print("r:"+bot[0]+"" + bot[1] + bot[2] + bot[3]);
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
         for(int i = row+1; i < 10; i++){
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
         for(int i = col+1; i < 10; i++){
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
      for(int i = 0; i < 10; i++){
         for(int j = 0; j < 10; j++){
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
      for(int i = 0; i< 10; i++){
         for (int j = 0; j < 10; j++){
            int randy = (int) (Math.random()*2);
            if (board [i][j] < 2){
               int possibles = getNumPossible(lengths, board, i, j);
               //System.out.print(possibles + " ");
               if (possibles > max || possibles == max && randy == 0 ){
                  max = possibles;
                  coordinates [0] = i;
                  coordinates [1] = j;
               }
            }
         }
         //System.out.println();
      }
      return coordinates;
   }

   public static int getNumPossible (ArrayList <Integer> lengths, int [][] board,int row,int col){
      int poss = 0;
      int [] spacesAvailable = getSpaces(row, col, board);
      //System.out.println(spacesAvailable[0] + " " + spacesAvailable[1] + " " + spacesAvailable[2] + " " + spacesAvailable[3]);
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
         for(int i = row+1; i < 10; i++){
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
         for(int i = col+1; i < 10; i++){
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


   public boolean shotValidity (int row, int col){
      if (row < 10 && 0<= row && col < 10 && 0 <= col && board[row][col]<2){
         return true;
      }
      return false;
   }

   public boolean isSet (){
      return boats.isSet();
   }
}

