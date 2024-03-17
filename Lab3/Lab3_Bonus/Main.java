import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
                TravelPlan Iasi = new TravelPlan("Iasi");
                Tourist Iustin = new Tourist("Iustin", Iasi);

                Iasi.addTimeTable("2024-03-04", new Trip<>(LocalTime.of(15, 0), LocalTime.of(17, 0), "Advanced Programming", "frecventat", 5, Arrays.asList("luni", "miercuri", "joi")));
                Iasi.addTimeTable("2024-03-11", new Trip<>(LocalTime.of(18, 0), LocalTime.of(22, 0), "Java", "frecventat", 4, Arrays.asList("miercuri", "sambata")));
                Iasi.addTimeTable("2024-03-11", new Trip<>(LocalTime.of(10, 0), LocalTime.of(14, 0), "English", "nefrecventat", 4, Arrays.asList("joi", "duminica")));

                Iasi.addTimeTable("2024-03-14", new Trip<>(LocalTime.of(16, 0), LocalTime.of(18, 0), "Software Engineering", "frecventat", 6, Arrays.asList("luni", "marti", "joi")));

                Iasi.addTimeTable("2024-03-18", new Trip<>(LocalTime.of(16, 0), LocalTime.of(18, 0), "CDC", "frecventat", 5, Arrays.asList("luni")));

                Iasi.addTimeTable("2024-03-21", new Trip<>(LocalTime.of(8, 0), LocalTime.of(10, 0), "WEB Technologies", "nefrecventat", 4, Arrays.asList("joi", "vineri", "sambata")));
                Iasi.addTimeTable("2024-03-21", new Trip<>(LocalTime.of(10, 0), LocalTime.of(12, 0), "Physical education", "nefrecventat", 1, Arrays.asList("luni", "marti", "miercuri", "joi", "vineri")));
                Iasi.addTimeTable("2024-03-21", new Trip<>(LocalTime.of(14, 0), LocalTime.of(14, 0), "Operating system", "frecventat", 6, Arrays.asList("sambata", "duminica", "vineri")));
                Iasi.addTimeTable("2024-03-21", new Trip<>(LocalTime.of(14, 0), LocalTime.of(16, 0), "LFAC", "nefrecventat", 5, Arrays.asList("vineri", "duminica")));

                Iasi.addTimeTable("2024-03-28", new Trip<>(LocalTime.of(14, 0), LocalTime.of(18, 0), "OOP", "nefrecventat", 5, Arrays.asList("vineri", "joi")));

                Iustin.showScheduleTourist();

                System.out.println("-----------------------------------------");
                Iasi.scheduleDay("joi");
                System.out.println("-----------------------------------------");

                Iasi.printGraph(Iasi.buildGraphForDay("joi"));

                System.out.println("Graful pentru ziua de joi");
                Iasi.colorAndPrintGraph(Iasi.buildGraphForDay("joi"));

                System.out.println("-----------------------------------------");
                Iasi.printGraph(Iasi.buildGraphForDay("luni"));

                System.out.println("Graful pentru ziua de luni");
                Iasi.colorAndPrintGraph(Iasi.buildGraphForDay("luni"));
                System.out.println("-----------------------------------------");

        }
}
