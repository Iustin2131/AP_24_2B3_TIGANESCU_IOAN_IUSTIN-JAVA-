package org.example;

import java.util.*;

public class Game {
    private String gameId;
    private List<String> players;
    private Map<String, Grid> playerGrids;
    private boolean inProgress;

    public Game(String gameId) {
        this.gameId = gameId;
        this.players = new ArrayList<>();
        this.playerGrids = new HashMap<>();
        this.inProgress = false;
    }

    public boolean addPlayer(String player) {
        if (players.size() < 2) {
                players.add(player);
                Grid grid = new Grid();
                playerGrids.put(player, grid);

            return true;
        } else {
            return false;
        }
    }

    public void removePlayer(String player) {
        players.remove(player);
        playerGrids.remove(player);
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public String getOtherPlayer(String player) {
        for (String p : players) {
            if (!p.equals(player)) {
                return p;
            }
        }
        return null;
    }

    public String endGame(String winner) {
        inProgress = false;
        return "Game over! Winner is " + winner;
    }

    public String getGridForPlayer(String player) {
        Grid grid = playerGrids.get(player);
        return grid != null ? grid.getGrid() : "No grid available.";
    }

    public String submitMove(String player, String target) {
        Grid opponentGrid = playerGrids.get(getOtherPlayer(player));
        if (opponentGrid == null) {
            return "No opponent in the game.";
        }
        boolean hit = opponentGrid.receiveShot(target);

        opponentGrid.markWithX(target);

        if (opponentGrid.areAllShipsSunk()) {
            inProgress = false;
            return "Hit! All ships sunk. " + player + " wins!";
        } else {
            return hit ? "Hit!" : "Miss!";
        }
    }
}
