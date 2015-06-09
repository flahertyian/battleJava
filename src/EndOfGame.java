import javax.swing.JPanel;
import javax.swing.JFrame;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.awt.image.BufferedImage;


public class EndOfGame extends JFrame{
      BufferedImage winScreen;
      BufferedImage loseScreen;


  public EndOfGame(boolean winOrLoss){
    super.setSize(800,800);
    super.setResizable(false);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    super.setTitle("Game Over");
    super.setLocationRelativeTo(null);
    if(winOrLoss){
      winScreen();
    }else{
      loseScreen();
    }
    super.setVisible(true);
  }

   public void winScreen(){
      JPanel end = new JPanel();
      try {
          winScreen = ImageIO.read(new File("../resource/battleJavaMurica.png"));
       } catch (IOException ex) {
         ex.printStackTrace();
       }
       Graphics g = winScreen.getGraphics();
       g.drawImage(winScreen,0,0,this);
       end.paint(g);
       super.add(end);
   }

   public void loseScreen(){
      JPanel end = new JPanel();
      try {
          loseScreen = ImageIO.read(new File("../resource/battleJavaSoviet.png"));
       } catch (IOException ex) {
         ex.printStackTrace();
       }
       Graphics g = winScreen.getGraphics();
       g.drawImage(winScreen,0,0,this);
       end.paint(g);
       super.add(end);

   }

}
