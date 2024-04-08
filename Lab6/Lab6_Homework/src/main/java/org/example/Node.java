package org.example;

import java.io.Serializable;

public class Node implements Serializable {
    private boolean hasNorthEdge;
    private boolean hasSouthEdge;
    private boolean hasEastEdge;
    private boolean hasWestEdge;

    public Node() {
        this.hasNorthEdge = false;
        this.hasSouthEdge = false;
        this.hasEastEdge = false;
        this.hasWestEdge = false;
    }

    public boolean hasNorthEdge() {
        return hasNorthEdge;
    }

    public void setNorthEdge(boolean hasNorthEdge) {
        this.hasNorthEdge = hasNorthEdge;
    }

    public boolean hasSouthEdge() {
        return hasSouthEdge;
    }

    public void setSouthEdge(boolean hasSouthEdge) {
        this.hasSouthEdge = hasSouthEdge;
    }

    public boolean hasEastEdge() {
        return hasEastEdge;
    }

    public void setEastEdge(boolean hasEastEdge) {
        this.hasEastEdge = hasEastEdge;
    }

    public boolean hasWestEdge() {
        return hasWestEdge;
    }

    public void setWestEdge(boolean hasWestEdge) {
        this.hasWestEdge = hasWestEdge;
    }
}
