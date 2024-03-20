import java.util.*;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.TextStyle;


public class TravelPlan {
    private Map<String, List<Trip>> timeTable;
    private String city;

    public String getCity() {
        return city;
    }

    public TravelPlan(String city) {
        timeTable = new HashMap<>();
        this.city = city;
    }

    public Map<String, List<Trip>> getTimeTable() {
        return timeTable;
    }

    public void addTimeTable(String date, Trip interval) {
        if (timeTable.containsKey(date)) {
            List<Trip> intervals = timeTable.get(date);
                intervals.add(interval);

//            timeTable = {
//                     "2024-03-13": [Trip1, Trip2],
//                     "2024-03-14": [Trip3],
//                     "2024-03-15": [Trip4, Trip5, Trip6, Trip7]
//}

        } else {
            List<Trip> intervals = new ArrayList<>();
            intervals.add(interval);
            timeTable.put(date, intervals);
        }
    }

    public void showSchedule() {
        if (timeTable.isEmpty()) {
            System.out.println("The schedule is empty.");
        } else {
            for (Map.Entry<String, List<Trip>> entry : timeTable.entrySet()) {
                System.out.println("Date: " + entry.getKey());
                for (Trip trip : entry.getValue()) {
                    System.out.println("Activity: " + trip.getActivity());
                    System.out.print("Schedule: ");
                    for (Object schedule : trip.getSchedule()) {
                        System.out.print(schedule + " ");
                    }
                    System.out.println("\n" + trip.showOpeningHour());
                    System.out.print("\n\n");
                }
            }
        }
    }

    public void scheduleDay(String day) {
        day = day.trim().toLowerCase();

        for (Map.Entry<String, List<Trip>> entry : timeTable.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey());

            DayOfWeek dayOfWeek = date.getDayOfWeek();

            String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("ro")).toLowerCase();

            if (dayName.equals(day)) {
                System.out.println("\n\n Orarul zilei " + dayName + ": ");
                for (Trip interval : entry.getValue()) {
                    System.out.println("Date: " + entry.getKey() + " Schedule: " + interval.getStartHour() + " " + interval.getEndHour() + " " + interval.getActivity());
                    System.out.println(interval.showOpeningHour() + "\n");
                }
            }
        }
    }

    public Graph buildGraphForDay(String day) {
        day = day.trim().toLowerCase();
        List<Trip> dayTrips = new ArrayList<>();

        for (Map.Entry<String, List<Trip>> entry : timeTable.entrySet()) {
            LocalDate date = LocalDate.parse(entry.getKey());
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayName = dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("ro")).toLowerCase();

            if (dayName.equalsIgnoreCase(day)) {
                for (Trip trip : entry.getValue()) {
                    if (trip.getSchedule().contains(dayName)) {
                        dayTrips.add(trip);
                    }
                }
            }
        }

        dayTrips.sort(Comparator.comparing(Trip::getStartHour));
        Graph graph = new Graph(24);

        for (Trip trip : dayTrips) {
            int startHour = trip.getStartHour().getHour();
            int endHour = trip.getEndHour().getHour();

                graph.addEdge(startHour, endHour);
        }

        return graph;
    }

    public void printGraph(Graph graph) {
        for (int i = 0; i < 24; i++) {
            List<Integer> edges = graph.getEdges(i);
            System.out.print("Ora " + i + ": ");
            for (int edge : edges) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }


    public static void colorAndPrintGraph(Graph graph) {
        Coloring coloringProblem = new Coloring();

        int[][] adjacencyMatrix = convertGraphToAdjacencyMatrix(graph);
        int m = 23; // Numărul de culori disponibile

        coloringProblem.graphColoring(adjacencyMatrix, m);
    }

    public static int[][] convertGraphToAdjacencyMatrix(Graph graph) {
        int[][] adjacencyMatrix = new int[graph.getSize()][graph.getSize()];

        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getEdges(i);
            for (int neighbor : neighbors) {
                adjacencyMatrix[i][neighbor] = 1;
                adjacencyMatrix[neighbor][i] = 1;
            }
        }

        return adjacencyMatrix;
    }


}