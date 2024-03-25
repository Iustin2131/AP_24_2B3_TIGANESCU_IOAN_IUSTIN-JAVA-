package org.example;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

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
            System.out.println("Soferul/ita ->" + driver.getName() + "<- are ca pasager ->" +  passenger.getName() + "<-");
        }
    }

    public void createRandomGroupAndFilter() {
        matches.clear();
        Random random = new Random();
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Person person = new Person("Person" + i,random.nextInt(25), "Destination" );
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

    public void getSortedDriversByAge()
    {
        System.out.println("Sorted drivers by age : ");
        List<Person> sortDriversList = drivers.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(sortDriversList + "\n");
    }

   public void getSortedPassengersByName()
    {
        System.out.println("Sorted drivers by age : ");
        List<Person> sortDriversList = passengers.stream()
                        .sorted(Comparator.comparing(Person::getName))
                                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(sortDriversList + "\n");
    }


}
