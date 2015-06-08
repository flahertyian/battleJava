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

  public PlayerPanel(int sizeX, int sizeY){
    this.sizeY = sizeY;
    this.sizeX = sizeX;
    this.grid = new GridLayout(10,10);
    this.panel = new JPanel(grid);
    second = false;
    makeButtonArr();
    drawBoard();

  }

  private void makeButtonArr(){
    playerBoard = new Point[sizeX][sizeY];
    for(int y = 0; y<sizeY; y++){
      for(int x =0; x<sizeX; x++){
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

              if(curHeadDim.getWidth() == curTailDim.getWidth()||
                curHeadDim.getHeight() == curTailDim.getHeight()){
                int xDif = (int)Math.abs((Double)(curHeadDim.getWidth() - curTailDim.getWidth()));
                int yDif = (int)Math.abs((Double)(curHeadDim.getHeight() - curTailDim.getHeight()));

                if(curTailDim.getHeight()>curHeadDim.getHeight()){//below (y+)
                  for(int t = 0;t<= yDif; t++){
                    playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()+t].setShip();
                  }
                }else if(curTailDim.getHeight()<curHeadDim.getHeight()){ //above (y-)
                  for(int t = 0;t<= yDif; t++){
                    playerBoard[(int)curHeadDim.getWidth()][(int)curHeadDim.getHeight()-t].setShip();
                  }
                }else if(curTailDim.getWidth()>curHeadDim.getWidth()){ // right (x+)
                  for(int t = 0; t<= xDif; t++){
                    playerBoard[(int)curHeadDim.getWidth()+t][(int)curHeadDim.getHeight()].setShip();
                  }
                }else if(curTailDim.getWidth()<curHeadDim.getWidth()){ //left (x-)
                  for(int t = 0; t <= xDif; t++){
                    playerBoard[(int)curHeadDim.getWidth()-t][(int)curHeadDim.getHeight()].setShip();
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

    //will return the state of the ships and their locations
    public Point[][] getGameState(){
      return playerboard;
    }

  }

