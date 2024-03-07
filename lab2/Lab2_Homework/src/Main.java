import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 *  Main class
 */

public class Main {

    /**
     *  The main method initializes, calls and shows the result
     * @param  //The arguments(args) received from the command line.
     */
    public static void main(String[] args) {

        // Creating a problem and its solution
        Solution solutieProblema1 = new Solution("a+b");
     Problem problema1= new Problem("Suma a+b", solutieProblema1);

     // Desplaying the problem and i
     System.out.println("Problema " + problema1.getProblemDescription() + " are solutia " + solutieProblema1.getSolution());

     // Creatin depots and vehicles
     Depot depo1 =new Depot("Dep1");
     depo1.addVehicle(new Truck("Truck 1",1, "5 t"));
     depo1.addVehicle(new Truck("Truck 2",2, "4 t"));

     Depot depo2 =new Depot("Dep1");
     depo2.addVehicle(new Truck("Car 1", 3,"5 t"));
     depo2.addVehicle(new Truck("Car 2", 4,"4 t"));
     Depot depo3 =new Depot("Dep2");

      // Adding depots to the problem
        problema1.addDepot(depo1);
        problema1.addDepot(depo2);
        problema1.addDepot(depo3);

        //Displaying comparison betweem depots
     System.out.println("Dep1 si Dep2 " + depo1.equals(depo2));
     System.out.println("Dep1 si Dep3 " + depo1.equals(depo3));

     //Creating new vehicles
     Vehicle veh1 =new Truck("Truck1" ,1," 10 locuri");
     Vehicle veh2 =new Truck("Truck1", 2," 10 locuri");
     Vehicle veh3 =new Truck("Truck2" , 3," 100 locuri");

     //Desplaying camparison between vehicles
     System.out.println("Veh1 si Veh2 " + veh1.equals(veh2));
     System.out.println("Veh1 si Veh3 " + veh1.equals(veh3));

     //Desplaying vehicles capacities
        veh1.desplayCapacity();
        veh2.desplayCapacity();
        veh3.desplayCapacity();

        //Creating drons
        Vehicle dron1 = new Dron("Dron1", 1,"0 locuri");
        Vehicle dron2 = new Dron("Dron2", 2,"1 locuri");
        Vehicle dron3 = new Dron("Dron3", 3,"2 locuri");

        //Desplaying drons capacities
        dron1.desplayCapacity();
        dron2.desplayCapacity();
        dron3.desplayCapacity();

        //Creating clients
        Location client1 = new Location("Client1");
        Location client2 = new Location("Client2");
        Location client3 = new Location("Client3");
        Location client4 = new Location("Client4");

        //Allocation
        List<Location> clientLocations = new ArrayList<>();
        clientLocations.add(client1);
        clientLocations.add(client2);
        clientLocations.add(client3);
        clientLocations.add(client4);

        problema1.setClientLocation(clientLocations);

        assignClientsToVehicles(problema1);

    }

    /**
     * Assings clients to vehicles based on shortest travel time
     *
     * @param problem
     */
    private static void assignClientsToVehicles(Problem problem) {
        List<Location> clientLocations = problem.getClientLocations();
        List<Depot> depots = problem.getDepots();

        // Randomly generate travel times for demonstration
        Random random = new Random();
        int[][] travelTimes = new int[clientLocations.size()+1][clientLocations.size()+1];
        for (int i = 0; i < clientLocations.size(); i++) {
            for (int j = 0; j < clientLocations.size(); j++) {
                travelTimes[i][j] = random.nextInt(20) + 1;
            }
        }

        for (Location client : clientLocations) {
            double shortestTime = Double.MAX_VALUE;
            Vehicle assignedVehicle = null;

            for (Depot depot : depots) {
                for (Vehicle vehicle : depot.getVehicles()) {
                    double totalTime = calculateTotalTime(client, vehicle, travelTimes);
                    if (totalTime < shortestTime) {
                        shortestTime = totalTime;
                        assignedVehicle = vehicle;
                    }
                }
            }

            if (assignedVehicle != null) {
                System.out.println("Client " + client.getDescription() + " assigned to " + assignedVehicle.getDescription()
                        + " with total time " + shortestTime);
            } else {
                System.out.println("No vehicle available for client " + client.getDescription());
            }
        }
    }

    private static double calculateTotalTime(Location client, Vehicle vehicle, int[][] travelTimes) {
        int clientIndex = client.getIndex();
        int vehicleIndex = vehicle.getIndex();

        return travelTimes[clientIndex][vehicleIndex];
    }
}