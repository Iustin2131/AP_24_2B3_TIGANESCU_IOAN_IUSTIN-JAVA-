package org.example;
import com.github.javafaker.Faker;
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
import java.util.Arrays;

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

        List<Person> matchedPassengers = passengers.stream()
                .filter(passenger -> drivers.stream()
                         .anyMatch(driver -> driver.getDestination().equals(passenger.getDestination())))
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
            System.out.println("Soferul/ita ->" + driver.getName() + "<- are ca pasager ->" +  passenger.getName() + "<-" + " cu destinatia " + passenger.getDestination());
        }
    }

    public void createRandomGroupAndFilter() {
        matches.clear();
        Random random = new Random();
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person("Person" + i,random.nextInt(25), "Destination" + random.nextInt(4));
            persons.add(person);
        }


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

        LinkedList<Person> sortedDrivers = new LinkedList<>(drivers);

        Collections.sort(sortedDrivers, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p2.getAge(), p1.getAge());
            }
        });

        return sortedDrivers;
    }

    public TreeSet<Person> getSortedPassengersByName() {

        TreeSet<Person> sortedPassengers = new TreeSet<>(passengers);

        return sortedPassengers;
    }
    public LinkedList<String> getAllDestinations() {
        LinkedList<String> allDestinations = drivers.stream()
                .map(Person::getDestination)
                .distinct()
                .collect(Collectors.toCollection(LinkedList::new));

        allDestinations.addAll(passengers.stream()
                .map(Person::getDestination)
                .distinct()
                .collect(Collectors.toList()));

        return allDestinations.stream().distinct().collect(Collectors.toCollection(LinkedList::new));
    }

    public Map<String, String> destinationPassengers() {
        Map<String, String> destinationPassengers = passengers.stream()
                .collect(Collectors.groupingBy(
                        Person::getDestination,
                        Collectors.mapping(Person::getName, Collectors.joining(" , "))));

        drivers.forEach(driver ->
                destinationPassengers.merge(driver.getDestination(),
                        driver.getName(),
                        (passengerNames, driverName) -> passengerNames + " , " + driverName));

        return destinationPassengers;
    }

    public void generateRandomFake() {
        Faker faker = new Faker();
        Random random=new Random();
        matches.clear();
        passengers.clear();
        drivers.clear();
        drivers = new ArrayList<>();
        passengers = new ArrayList<>();
        matches = new HashMap<>();

        int randomNumberOfPassengers=random.nextInt(11)+5;
        int randomNumberOfDrivers=random.nextInt(11)+5;

        List<String> adrese = Arrays.asList(
                "Empire State Building",
                "Burj Khalifa",
                "Universitatea „Alexandru Ioan Cuza” din Iași"
        );

        for(int i=0;i<randomNumberOfPassengers;i++){

            String firstName = faker.name().firstName();
            int randomAge=random.nextInt(50);
            String streetAddress = adrese.get(random.nextInt(adrese.size()));
            addPassenger(new Person(firstName,randomAge, streetAddress ));
        }

        for(int i=0;i<randomNumberOfDrivers;i++){

            String firstName = faker.name().firstName();
            int randomAge=random.nextInt(50);
            String streetAddress = adrese.get(random.nextInt(adrese.size()));
            addDriver(new Person(firstName,randomAge, streetAddress ));
        }
    }

}
