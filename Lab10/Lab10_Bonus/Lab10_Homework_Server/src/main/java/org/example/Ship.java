package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Ship {
    private String name;
    private int size;
    private List<String> coordinates;
    private List<String> hits;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.coordinates = new ArrayList<>();
        this.hits = new ArrayList<>();
    }
    public boolean isHit(String target) {
        return coordinates.contains(target);
    }

    public boolean isSunk(Set<String> hits) {
        return hits.containsAll(coordinates);
    }
}