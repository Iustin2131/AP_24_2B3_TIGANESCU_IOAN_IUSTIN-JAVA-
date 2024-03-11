import java.util.ArrayList;
import java.util.List;

/**
 * The Problem class represents a problem in the domain.
 * It contains a description of the problem, a solution, depots, and client locations.
 */
public class Problem {
   private String description;
   private Solution solution;
   private List<Depot> depots;
   private List<Location> clientLocation;

   /**
    * Constructs a new Problem with the given description and solution.
    *
    * @param n The description of the problem.
    * @param s The solution to the problem.
    */
   public Problem (String n, Solution s){
      description=n;
      solution=s;
      depots= new ArrayList<>();
      clientLocation= new ArrayList<>();
   }

   /**
    * Gets the description of the problem.
    *
    * @return
    */
   public String getProblemDescription(){
      return description;
   }

   /**
    * Gets the number of cars.
    *
    * @return
    */
   public int getNumberOfCars(){
      int nrCars=0;
      for(Depot depot : depots){
         nrCars=nrCars+ depot.getVehicleSize();
      }

      return nrCars;
   }

   /**
    * Gets the solution of the problem.
    *
    * @return
    */
   public Solution getProblemSolution(){
      return solution;
   }

   /**
    * Adds a depot to the list of depots associated with the problem.
    *
    * @param depot The depot to be added.
    */
   public void addDepot(Depot depot){
      depots.add(depot);
   }

   /**
    * Gets the list of client locations associated with the problem.
    *
    * @return
    */
   public List<Location> getClientLocations() {
      return clientLocation;
   }

   /**
    * Gets the list of depots associated with the problem.
    *
    * @return
    */
   public List<Depot> getDepots() {
      return depots;
   }

   public List<Location> getClientLocation() {
      return clientLocation;
   }

   /**
    * Add  clients
    *
    * @param clientLocatio
    */

   public void add(Location clientLocatio) {
      boolean clientExists=false;

      for(Location client : clientLocation){
         if(client.getDescription().equals(clientLocatio.getDescription())){
            System.out.println("The Client " + clientLocatio.getDescription() + " is already exists. ");
            clientExists=true;
            break;
      }
   }
      if(!clientExists){
         clientLocation.add(clientLocatio);
      }
}
}
