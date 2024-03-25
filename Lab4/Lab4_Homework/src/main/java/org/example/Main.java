package org.example;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Organization organization= new Organization();

        organization.addDriver(new Person("Iustin",21, "Universitatea „Alexandru Ioan Cuza” din Iași" ));
        organization.addDriver(new Person("Pintecan", 20,"Universitatea „Alexandru Ioan Cuza” din Iași" ));
        organization.addDriver(new Person("Mihaela", 18,"Universitatea „Ștefan cel Mare” din Suceava " ));

        organization.addPassenger(new Person("Stefan", 20,"Universitatea „Ștefan cel Mare” din Suceava " ));
        organization.addPassenger(new Person("George",19, "Universitatea „Alexandru Ioan Cuza” din Iași" ));
        organization.addPassenger(new Person("Daria",15, "Universitatea „Alexandru Ioan Cuza” din Iași" ));

        System.out.println("------------------------------------------");
        organization.matchDriversAndPassengers();
        organization.printMatchesDriversPassengers();

        System.out.println("------------------------------------------");
        organization.createRandomGroupAndFilter();
        organization.printMatchesDriversPassengers();

        LinkedList<Person> sortedDrivers = organization.getSortedDriversByAge();
        System.out.println("Lista șoferilor sortată după vârstă:");
        for (Person driver : sortedDrivers) {
            System.out.println(driver.getName() + " - " + driver.getAge());
        }

        System.out.println("------------------------------------------\n");
        TreeSet<Person> sortedPassengers = organization.getSortedPassengersByName();

        System.out.println("Lista pasagerilor sortată după nume:");
        for (Person passenger : sortedPassengers) {
            System.out.println(passenger.getName() + " - " + passenger.getAge());
        }

        System.out.println("------------------------------------------\n");
        LinkedList<String> allDestinations= organization.getAllDestinations();
        System.out.println("Toate destinațiile prin care trec șoferii: " + allDestinations);

        System.out.println("------------------------------------------\n");
        Map<String, String> destinationPassengersMap = organization.destinationPassengers();
        System.out.println("Destination and people who want to go there: ");
        destinationPassengersMap.forEach((destination, people) ->
                System.out.println("Destination: " + destination + ", People: " + people));

        System.out.println("------------------------------------------\n");
        organization.generateRandomFake();
        organization.matchDriversAndPassengers();
        organization.printMatchesDriversPassengers();
    }
}