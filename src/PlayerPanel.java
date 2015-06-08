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
  private int[][] intBoard;

  public PlayerPanel(int sizeX, int sizeY, int[][] intBoard){
    this.sizeY = sizeY;
    this.sizeX = sizeX;
    this.grid = new GridLayout(10,10);
    this.panel = new JPanel(grid);
    second = false;
    this.intBoard = intBoard;
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

              // 0 no boat, 1 boat, 2 miss, 3 hitt
              if(curHeadDim.getWidth() == curTailDim.getWidth()||
                curHeadDim.getHeight() == curTailDim.getHeight()){
                int xDif = (int)Math.abs((Double)(curHeadDim.getWidth() - curTailDim.getWidth()));
                int yDif = (int)Math.abs((Double)(curHeadDim.getHeight() - curTailDim.getHeight()));

                if(curTailDim.getHeight()>curHeadDim.getHeight()){//below (y+)
                  Ship boat = new Ship(yDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),2);
                  if(testValidity(boat)){
                    for(int t = 0;t<= yDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()+t].setShip();
                      intBoard[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()+t] = 1;
                    }
                  }

                }else if(curTailDim.getHeight()<curHeadDim.getHeight()){ //above (y-)
                  Ship boat = new Ship(yDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),0);
                  if(testValidity(boat)){
                    for(int t = 0;t<= yDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()-t].setShip();
                      intBoard[(int)curHeadDim.getHeight()][(int)curHeadDim.getWidth()-t] = 1;
                    }
                  }
                }else if(curTailDim.getWidth()>curHeadDim.getWidth()){ // right (x+)
                  Ship boat = new Ship(xDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),1);
                  if(testValidity(boat)){
                    for(int t = 0; t<= xDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()+t][(int)curHeadDim.getHeight()].setShip();
                      intBoard[(int)curHeadDim.getHeight()+t][(int)curHeadDim.getWidth()] = 1;
                    }
                  }
                }else if(curTailDim.getWidth()<curHeadDim.getWidth()){ //left (x-)
                  Ship boat = new Ship(xDif,(int)curHeadDim.getHeight(),(int)curHeadDim.getWidth(),3);
                  if(testValidity(boat)){
                    for(int t = 0; t <= xDif; t++){
                      playerBoard[(int)curHeadDim.getWidth()-t][(int)curHeadDim.getWidth()].setShip();
                      intBoard[(int)curHeadDim.getHeight()-t][(int)curHeadDim.getWidth()] = 1;
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
      for (int i = 0; i < boat.getLength(); i++){
         if (boat.getCoordinate(i, 0) >= sizeY || boat.getCoordinate(i,1) >= sizeX){
            return false;
         }
         if (intBoard[boat.getCoordinate(i,0)][boat.getCoordinate(i,1)]==1){
            System.out.println("test vil false! ahhh");
            return false;
         }
      }
      return true;
   }

  }

