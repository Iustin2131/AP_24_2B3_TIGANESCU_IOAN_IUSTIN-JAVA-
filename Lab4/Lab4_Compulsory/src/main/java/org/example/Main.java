package org.example;
import java.util.LinkedList;
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

        // Obținem lista sortată a șoferilor după vârstă
        LinkedList<Person> sortedDrivers = organization.getSortedDriversByAge();

        // Afișăm șoferii sortați după vârstă
        System.out.println("Lista șoferilor sortată după vârstă:");
        for (Person driver : sortedDrivers) {
            System.out.println(driver.getName() + " - " + driver.getAge());
        }

        // Obținem TreeSet-ul sortat cu pasagerii după nume
        TreeSet<Person> sortedPassengers = organization.getSortedPassengersByName();

        // Afișăm pasagerii sortați după nume
        System.out.println("Lista pasagerilor sortată după nume:");
        for (Person passenger : sortedPassengers) {
            System.out.println(passenger.getName() + " - " + passenger.getAge());
        }
    }
}