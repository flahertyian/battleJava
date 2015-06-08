import java.util.*;

public class Fleet {

   private ArrayList<Ship> boats;
   
   public Fleet () {
      this.boats = new ArrayList <Ship> ();
   }
   
   public boolean floating (){
      for(Ship boat : this.boats){
         if(boat.getLength()-boat.getHits()>0){
            return true;
         }
      }
      return false;
   }
   
   public void hit (int row,int col, Point [][] enemyBoard){
      for(Ship boat: this.boats){
         for(int i = 0; i < boat.getLength(); i++){
            if(boat.getCoordinate(i,0) == row & boat.getCoordinate(i,1) == col){
               boat.hit();
               if (boat.getHits() == boat.getLength()){
                  for(int j = 0; j<boat.getLength(); j++){
                     System.out.print(boat.getCoordinate(j,0) + "" + boat.getCoordinate(j,1));
                     enemyBoard[boat.getCoordinate(j,1)][boat.getCoordinate(j,0)].setShip();
                  }
                  
               }
            }
         }
      }
   }
   
   public ArrayList<Ship> getBoats (){
      return this.boats;
   }
   
   public void addShip (Ship boat){
      this.boats.add(boat);
   }
   
   public int getShortestBoard() {
      ArrayList<Integer> lengths = new ArrayList <Integer> ();
      for(Ship boat : this.boats){
         if (boat.getHits() != boat.getLength()){
            lengths.add(boat.getLength());
         }
      }
      int min = lengths.get(0);
      for(int i = 1; i<lengths.size(); i++){
         if (lengths.get(i)<min){
            min = lengths.get(i);
         }
      }
      return min;
   }
   
   public ArrayList <Integer> getAllLengths() {
      ArrayList<Integer> lengths = new ArrayList <Integer> ();
      for(Ship boat : this.boats){
         if (boat.getHits() != boat.getLength()){
            lengths.add(boat.getLength());
         }
      }
      return lengths;
   }
}