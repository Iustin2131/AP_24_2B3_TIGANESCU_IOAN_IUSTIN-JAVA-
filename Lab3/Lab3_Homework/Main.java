import java.time.LocalTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        public static void main(String[] args){

                TravelPlan Iasi= new TravelPlan("Iasi");

                Iasi.addTimeTable("2024-03-13", new Trip<>(LocalTime.of(10, 0), LocalTime.of(12, 0), "Advanced Programming","frecventat",5));
                Iasi.addTimeTable("2024-03-13", new Trip<>(LocalTime.of(10, 0), LocalTime.of(12, 0), "CDC",  "nefrecventat",4));
                Iasi.addTimeTable("2024-03-14", new Trip<>(LocalTime.of(16, 0), LocalTime.of(18, 0), "Software Engineering", "frecventat",6));
                Iasi.addTimeTable("2024-03-15", new Trip<>(LocalTime.of(8, 0), LocalTime.of(10, 0), "WEB Technologies" ,"nefrecventat",4));
                Iasi.addTimeTable("2024-03-15", new Trip<>(LocalTime.of(10, 0), LocalTime.of(12, 0), "Physical education", "nefrecventat",1));
                Iasi.addTimeTable("2024-03-15", new Trip<>(LocalTime.of(12, 0), LocalTime.of(14, 0), "Operating system", "frecventat",6));
                Iasi.addTimeTable("2024-03-15", new Trip<>(LocalTime.of(14, 0), LocalTime.of(16, 0), "LFAC", "nefrecventat",5));

                Iasi.showSchedule();
                Iasi.scheduleDay("vineri");
                Iasi.sortByStartHour("nefrecventat");

        }
}