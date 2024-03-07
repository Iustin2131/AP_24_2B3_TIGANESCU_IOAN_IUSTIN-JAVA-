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

   /**
    * Sets the list of client locations associated with the problem.
    *
    * @param clientLocation The list of client locations to be set.
    */
   public void setClientLocation(List<Location> clientLocation) {
      this.clientLocation = clientLocation;
   }
}
