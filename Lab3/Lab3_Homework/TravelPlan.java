import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.stream.Collectors;


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

            boolean isOccupied=false;
            List<Trip> intervals = timeTable.get(date);
            for (Trip existingInterval : intervals) {
                if (existingInterval.getStartHour().equals(interval.getStartHour()) && existingInterval.getEndHour().equals(interval.getEndHour())) {
                    System.out.println("The schedule is already occupied.");
                    isOccupied = true;
                    break;
                }
            }
            if(!isOccupied){
                intervals.add(interval);
            }
        } else {
            List<Trip> intervals = new ArrayList<>();
            intervals.add(interval);
            timeTable.put(date, intervals);
        }
    }

    public void showSchedule() {
        int ok = 0;
        if (timeTable.isEmpty()) {
            System.out.println("The schedule, hour and cost are empty.");
        } else {
            for (Map.Entry<String, List<Trip>> entry : timeTable.entrySet()) {
                for (Trip interval : entry.getValue()) {
                    System.out.println("Date: " +  entry.getKey() +  " Schedule: " + interval.getStartHour() + " " + interval.getEndHour() + " " + interval.getActivity());
                    System.out.println(interval.showOpeningHour()+ "\n");
                }
            }
        }
    }

    public void sortByStartHour(String visitable) {
        boolean found = false;
        for (Map.Entry<String, List<Trip>> entry : timeTable.entrySet()) {
            List<Trip> trips = entry.getValue().stream()   //obtinem fluxul de elemente din List
                    .filter(trip -> trip.getVisitable().equals(visitable))   // filtrăm activitățile în funcție de visitable (trip este o variabila predefinita pentru filter )
                    .sorted(Comparator.comparing(Trip::getStartHour))  // sortăm activitățile după ora de început
                    .collect(Collectors.toList());    // colectăm activitățile filtrate și sortate într-o listă

            if (!trips.isEmpty()) {
                if (!found) {
                    System.out.println("Activitățile pentru " + visitable + ", sortate după oră de început:");
                    found = true;
                }

                for (Trip trip : trips) {
                    System.out.println(trip.showOpeningHour());
                }
            }
        }

        if (!found) {
            System.out.println("Nu s-au găsit activități pentru " + visitable);
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

}