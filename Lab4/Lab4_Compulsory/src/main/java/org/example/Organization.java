package org.example;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.TreeSet;

public class Organization {
    private List<Person> drivers;
    private List<Person> passengers;
    private  Map<Person, Person> matches ;


    public Organization() {
        drivers = new ArrayList<>();
        passengers = new ArrayList<>();
        matches = new HashMap<>();
    }

    public void addDriver(Person driver) {
        drivers.add(driver);
    }

    public void addPassenger(Person passenger) {
        passengers.add(passenger);
    }

    public  void matchDriversAndPassengers() {
        Map<Person, Person> matches = new HashMap<>();

        // Transformăm lista de pasageri într-un flux de elemente
        List<Person> matchedPassengers = passengers.stream()

                // Aplicăm un filtru pentru a păstra doar pasagerii care au aceeași destinație cu cel puțin un șofer
                .filter(passenger -> drivers.stream() // Transformăm lista de șoferi într-un flux de elemente

                        // Verificăm dacă oricare dintre șoferi are aceeași destinație ca și pasagerul curent
                        .anyMatch(driver -> driver.getDestination().equals(passenger.getDestination())))

                // Colectăm pasagerii rămași după filtrare înapoi într-o listă
                .collect(Collectors.toList());

        for (Person driver : drivers) {
            for (Person passenger : matchedPassengers) {
                if (driver.getDestination().equals(passenger.getDestination())) {
                    matches.put(driver, passenger);
                    matchedPassengers.remove(passenger);
                    break;
                }
            }
        }

        this.matches =matches;
    }
    public void printMatchesDriversPassengers() {
        System.out.println("Soferii si pasagerii atribuiti:");
        for (Map.Entry<Person, Person> entry : matches.entrySet()) {
            Person driver = entry.getKey();
            Person passenger = entry.getValue();
            System.out.println("Soferul/ita ->" + driver.getName() + "<- are ca pasager ->" +  passenger.getName() + "<-");
        }
    }

    public void createRandomGroupAndFilter() {
        matches.clear();
        Random random = new Random();
        List<Person> persons = new ArrayList<>();

        // Adăugăm persoane aleatorii în lista de persoane
        for (int i = 0; i < 10; i++) {
            Person person = new Person("Person" + i,random.nextInt(25), "Destination" );
            persons.add(person);
        }

        // Filtrăm șoferii și pasagerii folosind stream-uri Java
        List<Person> filteredDrivers = persons.stream()
                .filter(person -> person.getDestination().equals("Destination0") || person.getDestination().equals("Destination1"))
                .collect(Collectors.toList());

        List<Person> filteredPassengers = persons.stream()
                .filter(person -> person.getDestination().equals("Destination2") || person.getDestination().equals("Destination3"))
                .collect(Collectors.toList());

        for (int i = 0; i < Math.min(filteredDrivers.size(), filteredPassengers.size()); i++) {
            matches.put(filteredDrivers.get(i), filteredPassengers.get(i));
        }
    }
    public LinkedList<Person> getDriversLinkedList() {
        return new LinkedList<>(drivers);
    }

    public LinkedList<Person> getSortedDriversByAge() {
        // Copiem lista de șoferi pentru a nu afecta lista originală
        LinkedList<Person> sortedDrivers = new LinkedList<>(drivers);

        // Sortăm lista de șoferi după vârstă folosind un comparator personalizat
        Collections.sort(sortedDrivers, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p2.getAge(), p1.getAge());
            }
        });

        return sortedDrivers;
    }

    public TreeSet<Person> getSortedPassengersByName() {
        // Cream un TreeSet pentru a stoca pasagerii
        TreeSet<Person> sortedPassengers = new TreeSet<>(passengers);

        return sortedPassengers;
    }
}
