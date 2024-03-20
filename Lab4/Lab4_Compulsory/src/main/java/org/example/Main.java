package org.example;
import java.util.LinkedList;
import java.util.TreeSet;

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
        organization.getSortedDriversByAge();
        System.out.println("------------------------------------------");
        organization.getSortedPassengersByName();
    }
}