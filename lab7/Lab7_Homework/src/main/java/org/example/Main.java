package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int tokenMaxValue = 3;
        int playersNumber = 3;
        long gameDurationMillis = 60000;

        Bag bag = new Bag(tokenMaxValue);
        List<Player> players = new ArrayList<>();
        AtomicBoolean gameInProgress = new AtomicBoolean(true); // Indicator for game progress

        for (int i = 1; i <= playersNumber; i++) {
            players.add(new Player("Player " + i, bag, gameInProgress));
        }

        for (Player player : players) {
            player.start();
        }

        Timekeeper timekeeper = new Timekeeper(gameDurationMillis, gameInProgress);
        timekeeper.setDaemon(true);
        timekeeper.start();

        for (Player player : players) {
            player.join();
        }

        gameInProgress.set(false);

        if (timekeeper.isGameFinished()) {
            System.out.println("\n Game ended due to time limit exceeded. \n");
        } else {
            System.out.println("\n All players finished within the time limit. \n");
        }

        GameStatistics gameStatistics = new GameStatistics(players);
        gameStatistics.calculateScores();
        gameStatistics.displayScores();
        gameStatistics.determineWinner();
    }
}