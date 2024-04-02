package org.example;

import java.util.List;

public class Employee {
    private String name;
    private String company;
    private List<String> abilities;

    public Employee(String name, String company, List<String> abilities) {
        this.name = name;
        this.company = company;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public List<String> getAbilities() {
        return abilities;
    }
}
