package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public List<String> getHits() {
        return hits;
    }


    public boolean isHit(String target) {
        return coordinates.contains(target);
    }

    public boolean isSunk(Set<String> hits) {
        return hits.containsAll(coordinates);
    }
}