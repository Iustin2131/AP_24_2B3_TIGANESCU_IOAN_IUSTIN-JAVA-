package org.example;

public class Person implements Comparable<Person>{
    private String name;
    private String destination;
    private int age;


    public Person(String name, int age, String destination) {
        this.name = name;
        this.destination = destination;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return this.name.compareTo(otherPerson.getName());
    }
}
