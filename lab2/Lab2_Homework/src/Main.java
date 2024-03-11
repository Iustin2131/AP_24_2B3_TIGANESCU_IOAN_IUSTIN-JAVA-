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
     depo1.addVehicle(new Truck("Truck 1",1, 5));
     depo1.addVehicle(new Truck("Truck 2",2, 4));

     Depot depo2 =new Depot("Dep1");
     depo2.addVehicle(new Truck("Car 1", 3,5));
     depo2.addVehicle(new Truck("Car 2", 4,4));

     //testing to see if am i allowed to add the same depot a second time
     depo2.addVehicle(new Truck("Car 2", 4,4));
     Depot depo3 =new Depot("Dep2");

      // Adding depots to the problem
        problema1.addDepot(depo1);
        problema1.addDepot(depo2);
        problema1.addDepot(depo3);

        //Displaying comparison betweem depots
     System.out.println("Dep1 si Dep2 " + depo1.equals(depo2));
     System.out.println("Dep1 si Dep3 " + depo1.equals(depo3));

     //Creating new vehicles
     Vehicle veh1 =new Truck("Truck1" ,1,10);
     Vehicle veh2 =new Truck("Truck1", 2,10);
     Vehicle veh3 =new Truck("Truck2" , 3,100);

     depo1.addVehicle(veh1);
     //testing to see if am allowed to add the same vehicle a second time
     depo1.addVehicle(veh1);
     depo1.addVehicle(veh2);
     depo2.addVehicle(veh3);

     //Desplaying vehicle capacitie
        veh2.desplayCapacity();

        //Creating drons
        Vehicle dron1 = new Dron("Dron1", 1,10);
        Vehicle dron2 = new Dron("Dron2", 2,1);
        Vehicle dron3 = new Dron("Dron3", 3,2);
        Vehicle dron4 = new Dron("Dron4", 3,2);

        //Adding vehicles to depot
        depo1.addVehicle(dron4);
        depo1.addVehicle(dron1);
        depo1.addVehicle(dron2);
        depo1.addVehicle(dron3);
        depo1.addVehicle(dron3);

        //Desplaying dron capacitie
        dron1.desplayCapacity();

        //Creating clients
        Location client1 = new Location("Client1");
        Location client2 = new Location("Client2");
        Location client3 = new Location("Client3");
        Location client4 = new Location("Client4");
        Location client5 = new Location("Client5");
        //Allocation
        problema1.add(client1);
        problema1.add(client2);
        problema1.add(client3);
        //testing to see if am allowed to add the same client a second time
        problema1.add(client3);
        problema1.add(client4);
        problema1.add(client5);

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
        int[][] travelTimes = new int[clientLocations.size()][problem.getNumberOfCars()];
        boolean[][] booleansTravelTimes = new boolean[clientLocations.size()][problem.getNumberOfCars()];

        for (int i = 0; i < clientLocations.size(); i++) {
            for (int j = 0; j < problem.getNumberOfCars(); j++) {
                travelTimes[i][j] = random.nextInt(20) + 1;
            }
        }
        //Desplaying the clients location
        for (int i = 0; i < clientLocations.size(); i++) {
            for (int j = 0; j < problem.getNumberOfCars(); j++) {
                System.out.print(travelTimes[i][j]+ "  ");
            }
            System.out.println();
        }

        //We go through the customers and after that we search for shortest road
        for (Location client : clientLocations) {
            double shortestTime = Double.MAX_VALUE;
            int variable1=-1,variavle2=-1;

                for (int i=0; i<problem.getNumberOfCars(); i++) {
                    if (travelTimes[client.getIndex()][i]< shortestTime && booleansTravelTimes[client.getIndex()][i]==false) {
                        shortestTime = travelTimes[client.getIndex()][i];
                        variable1=client.getIndex();
                        variavle2=i;
                    }
                }

            if (variable1!=-1) {
                booleansTravelTimes[variable1][variavle2]=true;
                System.out.println("Client " + client.getDescription() + " assigned to " + "Vehicle"+variavle2
                        + " with total time " + shortestTime);
            } else {
                System.out.println("No vehicle available for client " + client.getDescription());
            }
        }
    }
}