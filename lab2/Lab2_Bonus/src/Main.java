import java.sql.ClientInfoStatus;
import java.util.List;
import java.util.Random;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

       Graph graph = new Graph();
       Node A= new Node(0, "Depot1");
       Node B= new Node(1, "Depot2");
       Node C= new Node(2, "Depot3");
       Node D= new Node(3, "Depot4");

       Vehicle BMW = new Vehicle("BMV", 9);
       Vehicle Porsche = new Vehicle("Porsche", 5);
       Vehicle Bugatti = new Vehicle("Bugatti", 6);
       Vehicle Ferrari = new Vehicle("Ferrari", 4);

       A.add(BMW, BMW);
       B.add(Bugatti);
       C.add(Ferrari);
       D.add(Porsche);

       Node E = new Node(4, "Client1");
       Node F = new Node(5, "Client2");
       Node G = new Node(6, "Client3");
       Node H = new Node(7, "Client31");
       Node I = new Node(8, "Client4");
       Node J = new Node(9, "Client5");
       Node K = new Node(10, "Client6");
       Node L = new Node(11, "Client7");
       Node M = new Node(12, "Client8");
       Node N = new Node(13, "Client9");
       Node O = new Node(14, "Client10");
       Node P = new Node(15, "Client11");

       graph.addNode(A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P);

       Edge AE = new Edge(A, E, 3);
       Edge AB = new Edge(A, B, 4);
       Edge BF = new Edge(B, F, 2);
       Edge BC = new Edge(B, C, 1);
       Edge CG = new Edge(C, G, 7);
       Edge CD = new Edge(C, D, 5);
       Edge DH = new Edge(D, H, 3);

       Edge EI = new Edge(E, I, 1);
       Edge EF = new Edge(E, F, 2);
       Edge FJ = new Edge(F, J, 3);
       Edge FG = new Edge(F, G, 6);
       Edge GK = new Edge(G, K, 5);
       Edge GH = new Edge(G, H, 6);
       Edge HL = new Edge(H, L, 3);

       Edge IM = new Edge(I, M, 5);
       Edge IJ = new Edge(I, J, 2);
       Edge JN = new Edge(J, N, 4);
       Edge JK = new Edge(J, K, 7);
       Edge KO = new Edge(K, O, 1);
       Edge KL = new Edge(K, L, 3);
       Edge LP = new Edge(L, P, 6);

       Edge MN = new Edge(M, M, 4);
       Edge NO = new Edge(N, O, 5);
       Edge OP = new Edge(O, P, 10);

       int[][] costMatrixArray = {
               //A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P
               { 0, 4, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // A
               { 4, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // B
               { 0, 1, 0, 5, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // C
               { 0, 0, 5, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },  // D
               { 3, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },  // E
               { 0, 2, 0, 0, 2, 0, 6, 0, 0, 3, 0, 0, 0, 0, 0, 0 },  // F
               { 0, 0, 7, 0, 0, 6, 0, 6, 0, 0, 5, 0, 0, 0, 0, 0 },  // G
               { 0, 0, 0, 3, 0, 0, 6, 0, 0, 0, 0, 3, 0, 0, 0, 0 },  // H
               { 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 5, 0, 0, 0 },  // I
               { 0, 0, 0, 0, 0, 3, 0, 0, 2, 0, 7, 0, 0, 4, 0, 0 },  // J
               { 0, 0, 0, 0, 0, 0, 5, 0, 0, 7, 0, 3, 0, 0, 1, 0 },  // K
               { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 6 }, // L
               { 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 4, 0, 0 },  // M
               { 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 4, 0, 5, 0 },  // N
               { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 5, 0, 10 },  // O
               { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 10, 0 },  // P
       };

       assidnVehiclesToClients(graph, costMatrixArray);
       System.out.println("Randam problem ");
       RandomProblemGenerator();
    }
   public static Vehicle findNearestVehicle(Node client, List<Node> depots, int[][] costMatrixArray) {
      Node nearestDepot = null;
      int shortestDistance = Integer.MAX_VALUE;

      for (Node depot : depots) {
         if (client.getId() >= costMatrixArray.length || depot.getId() >= costMatrixArray.length) {
            System.out.println("Invalid client or depot id");
            return null;
         }

         if (costMatrixArray[depot.getId()][client.getId()] != 0) {
            int distance = costMatrixArray[depot.getId()][client.getId()];
            if (distance < shortestDistance) {
               shortestDistance = distance;
               nearestDepot = depot;
            }
         }
      }

      Vehicle nearestVehicle = null;
      if (nearestDepot == null) {
         return null;
      } else {
         List<Vehicle> vehicles = nearestDepot.getVehicles();

         for (Vehicle vehicle : vehicles) {
            if (vehicle.getFreeSeats() > 0) {
               vehicle.setOccupiedSeat();
               nearestVehicle = vehicle;
               break;
            }
         }
      }
      return nearestVehicle;
   }

   public static void RandomProblemGenerator() {

      Graph graph = generateRandomInstance(10, 20, 10, 20);
      displayGraph(graph);
      int[][] costMatrixArray = {
              //A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P
              { 0, 4, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // A
              { 4, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // B
              { 0, 1, 0, 5, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0 },  // C
              { 0, 0, 5, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },  // D
              { 3, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },  // E
              { 0, 2, 0, 0, 2, 0, 6, 0, 0, 3, 0, 0, 0, 0, 0, 0 },  // F
              { 0, 0, 7, 0, 0, 6, 0, 6, 0, 0, 5, 0, 0, 0, 0, 0 },  // G
              { 0, 0, 0, 3, 0, 0, 6, 0, 0, 0, 0, 3, 0, 0, 0, 0 },  // H
              { 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 5, 0, 0, 0 },  // I
              { 0, 0, 0, 0, 0, 3, 0, 0, 2, 0, 7, 0, 0, 4, 0, 0 },  // J
              { 0, 0, 0, 0, 0, 0, 5, 0, 0, 7, 0, 3, 0, 0, 1, 0 },  // K
              { 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 6 }, // L
              { 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 4, 0, 0 },  // M
              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 4, 0, 5, 0 },  // N
              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 5, 0, 10 },  // O
              { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 10, 0 },  // P
      };

      assidnVehiclesToClients(graph,  costMatrixArray);
   }
   public static Graph generateRandomInstance(int numDepots, int numClients, int numVehiclesPerDepot, int maxDistance) {
      Graph graph = new Graph();
      Random random = new Random();

      // Generate depots
      for (int i = 0; i < numDepots; i++) {
         Node depot = new Node(i, "Depot" + (i + 1));
         graph.addNode(depot);

         // Generate vehicles for the depot
         for (int j = 0; j < numVehiclesPerDepot; j++) {
            Vehicle vehicle = new Vehicle("Vehicle" + (j + 1), random.nextInt(10) + 1);
            depot.add(vehicle);
         }
      }

      // Generate clients
      for (int i = 0; i < numClients; i++) {
         Node client = new Node(numDepots + i, "Client" + (i + 1));
         graph.addNode(client);
      }

      // Generate random edges between depots and clients
      List<Node> depots = graph.getDepots();
      List<Node> clients = graph.getClients();

      for (Node depot : depots) {
         for (Node client : clients) {
            if (random.nextBoolean()) {
               int distance = random.nextInt(maxDistance) + 1;
               Edge edge = new Edge(depot, client, distance);
               graph.addEdge(edge);
            }
         }
      }

      return graph;
   }

   public static void displayGraph(Graph graph) {
      System.out.println("Graph representation:");
      for (Node node : graph.getClients()) {
         System.out.print(node.getDescription());

         // Afisare informatii despre vehiculele asociate nodurilor de tip depozit
         if (node.getDescription().startsWith("Depot")) {
            System.out.print(" [Vehicles: ");
            List<Vehicle> vehicles = node.getVehicles();
            for (int i = 0; i < vehicles.size(); i++) {
               System.out.print(vehicles.get(i).getDescription());
               if (i < vehicles.size() - 1) {
                  System.out.print(", ");
               }
            }
            System.out.print("]");
         }

         System.out.print(" -> ");

         for (Edge edge : graph.getEdges()) {
            System.out.print(edge.getDestination().getDescription() + "(" + edge.getID() + ") ");
         }
         System.out.println();
      }
   }

   public static void assidnVehiclesToClients(Graph graph,  int[][] costMatrixArray){
      List<Node> clients= graph.getClients();
      for(Node client: clients){
         Vehicle nearestVehicle= findNearestVehicle(client, graph.getDepots(), costMatrixArray);
         if(nearestVehicle!=null){
            client.setAssignedVehicle(nearestVehicle);
            nearestVehicle.setOccupiedSeat();
            System.out.println("The Client " + client.getDescription() + " has machine attribute " + client.getAssignedVehicle() );
         } else {
            System.out.println(" No available vehicle from the client " + client.getDescription());
         }
      }
   }
}
