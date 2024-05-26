package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Setter
@Getter
public class Grid {
    private char[][] grid;
    private List<Ship> ships;
    private Set<String> hits;
    private static int GRID_SIZE = 10;
    public Grid() {
        this.ships = new ArrayList<>();
        this.hits = new HashSet<>();
        this.grid = displayGrid();
    }
    public boolean receiveShot(String target) {
        hits.add(target);
        for (Ship ship : ships) {
            if (ship.isHit(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean areAllShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(hits)) {
                return false;
            }
        }
        return true;
    }

    public void placeShipsRandomly() {

        ships.add(new Ship("Battleship", 4));
        ships.add(new Ship("Cruiser", 3));
        ships.add(new Ship("Destroyer", 2));

        for (Ship ship : ships) {
            List<String> coordinates = generateRandomCoordinates(ship.getSize());
            ship.setCoordinates(coordinates);
        }
    }

    private List<String> generateRandomCoordinates(int size) {
        List<String> coordinates = new ArrayList<>();
        Random rand = new Random();
        char row = (char) (rand.nextInt(9) + 'A');
        int col = rand.nextInt(GRID_SIZE);

            coordinates.add(row + "" + col);

        return coordinates;
    }

    public char[][] displayGrid() {
        placeShipsRandomly();
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];

        // Inițializează grila cu '0' pentru locații goale
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '0';
            }
        }

        // Marchează locațiile navelor cu 'W'
        for (Ship ship : ships) {
            for (String pos : ship.getCoordinates()) {
                int row = pos.charAt(0) - 'A';
                int col = Character.getNumericValue(pos.charAt(1));
                grid[row][col] = 'W';
            }
        }

        // Marchează locațiile lovite cu 'X'
        for (String hit : hits) {
            int row = Character.getNumericValue(hit.charAt(0));
            int col = Character.getNumericValue(hit.charAt(1));
            grid[row][col] = 'X';
        }

        return grid;
    }

    public String getGrid() {
        String grids = "  0 1 2 3 4 5 6 7 8 9@";
        char rowLabel = 'A';
        for (int i = 0; i < grid.length; i++) {
            grids += rowLabel + " ";
            for (int j = 0; j < grid[i].length; j++) {
                grids += grid[i][j] + " ";
            }
            grids += "@";
            rowLabel++;
        }
        return grids;
    }

    public void markWithX(String coordinate) {
        int row = coordinate.charAt(0) - 'A';
        int col = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
        grid[row][col] = 'X';
    }

}
