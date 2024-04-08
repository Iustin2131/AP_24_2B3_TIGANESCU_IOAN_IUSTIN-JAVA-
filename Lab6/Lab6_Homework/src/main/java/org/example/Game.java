package org.example;

public class Game {
    private int[][] board;
    private int currentPlayer;

    public Game(int rows, int cols) {
        board = new int[rows][cols];
        currentPlayer = 0;
    }

}
