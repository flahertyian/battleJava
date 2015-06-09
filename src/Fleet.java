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
      if(this.boats.size()==0){
         return true;
      }
      return false;
   }
   
   public void hit (int row,int col, Point [][] ptBoard){
      for(Ship boat: this.boats){
         for(int i = 0; i < boat.getLength(); i++){
            if(boat.getCoordinate(i,0) == row & boat.getCoordinate(i,1) == col){
               boat.hit();
               if (boat.getHits() == boat.getLength()){
                  for(int j = 0; j<boat.getLength(); j++){
                     //System.out.print(boat.getCoordinate(j,0) + "" + boat.getCoordinate(j,1));
                     ptBoard[boat.getCoordinate(j,1)][boat.getCoordinate(j,0)].setSunk();
                  }
                  
               }
            }
         }
      }
   }
   
 public void hit (Point [][] ptBoard, int row,int col){
      for(Ship boat: this.boats){
         for(int i = 0; i < boat.getLength(); i++){
            if(boat.getCoordinate(i,0) == row & boat.getCoordinate(i,1) == col){
               boat.hit();
               if (boat.getHits() == boat.getLength()){
                  for(int j = 0; j<boat.getLength(); j++){
                     //System.out.print(boat.getCoordinate(j,0) + "" + boat.getCoordinate(j,1));
                     ptBoard[boat.getCoordinate(j,0)][boat.getCoordinate(j,1)].setShip();
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
   
   public ArrayList <Integer> getAllAvailableLengths(){
      ArrayList<Integer> lengths = new ArrayList <Integer> ();
      lengths.add(2);
      lengths.add(3);
      lengths.add(4);
      lengths.add(5);
      lengths.add(3);
      for(Ship boat: this.boats){
         Integer temp = boat.getLength();
         lengths.remove(temp);
      }
      return lengths;
   }
   
   public boolean isSet (){
      ArrayList <Integer> avalLengths = getAllAvailableLengths();
      if(avalLengths.size()==0){
         //System.out.println("HOLLLLA");
         return true;
      }
      return false;
   }
        
}