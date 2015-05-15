public class Ship {
   int length;
   int [][] coordinates;
   int hitsTaken;
   int direction;

   //Change row imput to letter

   //direction 0= vertical, 1 = horizontal
   public Ship (int length, int row, int col, int direction){
      this.direction = direction;
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


}
