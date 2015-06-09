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
  

  public PlayerPanel(int sizeX, int sizeY){
    this.sizeY = sizeY;
    this.sizeX = sizeX;
    this.grid = new GridLayout(10,10);
    this.panel = new JPanel(grid);
    second = false;
    this.board = new int [10][10];

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
        playerBoard[i][j].addActionListener(new ActionListener(){
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
                  Ship boat = new Ship(yDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),2);
                  if(testValidity(boat)){
                    for(int t = 0;t<= yDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()+t].setShip();
                      board[(int)curHeadDim.getHeight()+t][(int)curHeadDim.getWidth()] = 1;
                      System.out.print((int)curHeadDim.getHeight()+t+""+(int)curHeadDim.getWidth());
                    }
                  }

                }else if(curTailDim.getHeight()<curHeadDim.getHeight()){ //above (y-)
                  Ship boat = new Ship(yDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),0);
                  if(testValidity(boat)){
                    for(int t = 0;t<= yDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()-t].setShip();
                      board[(int)curHeadDim.getHeight()-t][(int)curHeadDim.getWidth()] = 1;
                    }
                  }
                }else if(curTailDim.getWidth()>curHeadDim.getWidth()){ // right (x+)
                  Ship boat = new Ship(xDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),1);
                  if(testValidity(boat)){
                    for(int t = 0; t<= xDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()+t][(int)curHeadDim.getHeight()].setShip();
                      board[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()+t] = 1;
                    }
                  }
                }else if(curTailDim.getWidth()<curHeadDim.getWidth()){ //left (x-)
                  Ship boat = new Ship(xDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),3);
                  if(testValidity(boat)){
                    for(int t = 0; t <= xDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()-t][(int)curHeadDim.getHeight()].setShip();
                      board[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()-t] = 1;
                    }
                  }
                }
                second = false;
              }else{
                System.out.println("invalid placement");
                //if you have time set some sort of error
              }

            }else{ // if it is the fist click (the start point of the ship)
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
            System.out.println("test vil false! ahhh");
            return false;
         }
      }
      return true;
   }
   
   public void takeCTurn(){
      //Fleet boats, int [][] board, int cpuLevel, int random){    
      int row = (int) (Math.random()*10);
      int col = (int) (Math.random()*10);
      //in t [] smartCoordinate = new int [2];
//       int length = boats.getShortestBoard();
//       if(cpuLevel >= 9){
//          smartCoordinate = bestGuess(board, boats);
//       }
//       if(cpuLevel == 8){
//          smartCoordinate = longestLengthStrategy (board);
//       }
//       if(cpuLevel == 7){
//          smartCoordinate = noSurrounded(row, col, board, length);
//       }
//       if(cpuLevel == 6){
//          smartCoordinate = noSurrounded(row, col, board, 2);
//       }
//       if(cpuLevel >= 6){
//          row = smartCoordinate [0];
//          col = smartCoordinate [1];   
//       }
//       if((cpuLevel >= 2 && random >= 8 )||(cpuLevel >= 3 && random >= 5)||(cpuLevel>=4 && random >= 3)||(cpuLevel>=5)){
//          ArrayList<Integer> rows = new ArrayList<Integer> ();
//          ArrayList<Integer> cols = new ArrayList<Integer> ();
//          findHit : {
//             for(Ship boat: boats.getBoats()){
//                if(boat.getHits() != 0 && boat.getHits() != boat.getLength()){
//                   for(int i = 0; i < boat.getLength(); i++){
//                      if(board [boat.getCoordinate(i,0)][boat.getCoordinate(i,1)] == 3){
//                         rows.add(boat.getCoordinate(i,0));
//                         cols.add(boat.getCoordinate(i,1));
//                      }
//                   }
//                   int rand = (int) (Math.random()*4);
//                   if(rows.size() > 1){
//                      if(rows.get(0) == rows.get(1)){
//                         int max = -1;
//                         int min = WIDTH;
//                         for(int j = 0; j< cols.size(); j++){
//                            if(cols.get(j)>max){
//                               max = cols.get(j)*1;
//                            }
//                            if(cols.get(j)<min){
//                               min = cols.get(j)*1;
//                            }
//                         }
//                         System.out.print(max + "" + min);
//                         if (max > min+cols.size()-1){
//                            for(int i = 1; i < max-min; i++){
//                               if(board[rows.get(0)][min+i] < 2){
//                                  col = min+i;
//                               }
//                            }
//                            row = rows.get(0);  
//                         }
//                         else { 
//                            int dir = 0;
//                            row = rows.get(0);
//                            col = getLateHit(cols, col, rand, cpuLevel, row, board, dir);
//                         }
//                      }
//                      else if (cols.get(0) == cols.get(1)){
//                         int max = -1;
//                         int min = HEIGHT;
//                         for(int j = 0; j< rows.size(); j++){
//                            if(rows.get(j)>max){
//                               max = rows.get(j)*1;
//                            }
//                            if(rows.get(j)<min){
//                               min = rows.get(j)*1;
//                            }
//                         }
//                         if (max > min+rows.size()-1){
//                            for(int i = 1; i < max-min; i++){
//                               if(board[min+i][cols.get(0)] < 2){
//                                  row = min+i;
//                               }
//                            }
//                            col = cols.get(0);  
//                         }                        
//                         else { 
//                            int dir = 1;
//                            col = cols.get(0);
//                            row = getLateHit(rows, row, rand, cpuLevel, col, board, dir);
//                         }
//                      }
//                   }
//                   
//                   
//                   else{
//                      row = rows.get(0);
//                      col = cols.get(0);
//                      if(cpuLevel == 10){
//                         int [] spaces = getSpaces(row, col, board);
//                         int max = 0;
//                         for(int i=0; i < 4; i++){
//                            if(spaces[i]> max){
//                               max = spaces[i];
//                               rand = i;
//                            }
//                         }
//                      }
//                      if(rand==0){
//                         row--;
//                      }
//                      else if (rand==1){
//                         col++;
//                      }
//                      else if (rand==2){
//                         row++;
//                      }
//                      else if (rand==3){
//                         col--;
//                      }
//                   }  
//                   break findHit;
//                }
//             }
//          }           
//       }          
      if(shotValidity(row, col)){
         board[row][col] += 2;
         //System.out.print("Your oppenent shot at " + row + col + " ");
         if (board[row][col] == 2){
            playerBoard[col][row].setMiss();//System.out.println("BUT MISSED!");
         }
         else if (board [row][col] == 3){
            //System.out.print("AND HIT");
            //boats.hit(playerBoard, row, col);
            playerBoard[col][row].setHit();
            //System.out.println(" YOUR SHIP!");
         }
         //printPBoard(board);         
      }
      else {
         takeCTurn();
      }     
   }
   
   public boolean shotValidity (int row, int col){
      if (row < 10 && 0<= row && col < 10 && 0 <= col && board[row][col]<2){
         return true;
      }
      return false;
   }

   }

//  }

