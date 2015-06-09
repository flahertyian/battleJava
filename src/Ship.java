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
   public Ship (int length, int row, int col, int direction){
      this.length = length;
      this.coordinates = new int [length] [2];
      for (int i = 0; i< length; i++){
         if (direction == 1){
            this.coordinates [i][0] = row;
            this.coordinates [i][1] = col+i;
            //System.out.println(this.coordinates [i][0] + " " + this.coordinates [i][1]);
         }
         else if (direction == 0){
            this.coordinates [i][0] = row - i;
            this.coordinates [i][1] = col ;
            //System.out.println(this.coordinates [i][0] + " " + this.coordinates [i][1]);
         }
         else if (direction == 2){
            this.coordinates [i][0] = row+i;
            this.coordinates [i][1] = col;
            //System.out.println(this.coordinates [i][0] + " " + this.coordinates [i][1]);
         
         }
         else if (direction == 3){
            this.coordinates [i][0] = row;
            this.coordinates [i][1] = col-i;
            //System.out.println(this.coordinates [i][0] + " " + this.coordinates [i][1]);
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

