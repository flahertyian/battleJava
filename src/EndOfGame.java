public class EndOfGame extends JFrame{


  public EndOfGame(boolean winOrLoss){
    if(winOrLoss){
      winScreen();
    }else{
      loseScreen();
    }
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
       end.paintComponent(g);
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
       end.paintComponent(g);
       super.add(end);

   }

}
