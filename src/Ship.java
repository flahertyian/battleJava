public class Ship {
   private int length;
   private int [][] coordinates;
   private int hitsTaken;

   //Change row imput to letter
   /*
         0-north
      3-west 1-east
         2-south
   */
   //direction 0= vertical, 1 = horizontal
   public Ship (int length, int row, int col, int direction){
      this.length = length;
      this.coordinates = new int [length] [2];
      for (int i = 0; i< length; i++){
         if (direction == 1){
            this.coordinates [i][0] = row;
            this.coordinates [i][1] = col+i;
         }
         else if (direction == 0){
            this.coordinates [i][0] = row + i;
            this.coordinates [i][1] = col;
         }
      }
      this.hitsTaken = 0;
   }

   public int getLength(){
      return this.length;
   }

   public int getCoordinate (int i, int j){
      return this.coordinates[i][j];
   }

   public int getHits () {
      return this.hitsTaken;
   }

   public void hit() {
      this.hitsTaken++;
   }
}

