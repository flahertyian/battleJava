import java.util.*;

public class Fleet {

   ArrayList<Ship> boats;

   public Fleet () {
      this.boats = new ArrayList <Ship> ();
   }

   public boolean floating (){
      for(Ship boat : boats){
         if(boat.length-boat.hitsTaken>0){
            return true;
         }
      }
      return false;
   }

   public void hit (int row,int col){
      for(Ship boat: this.boats){
         for(int i = 0; i < boat.length; i++){
            if(boat.coordinates[i][0] == row & boat.coordinates[i][1] == col){
               boat.hitsTaken++;
               if (boat.hitsTaken == boat.length){
                  System.out.print(" AND SUNK");
               }
            }
         }
      }
   }


}
