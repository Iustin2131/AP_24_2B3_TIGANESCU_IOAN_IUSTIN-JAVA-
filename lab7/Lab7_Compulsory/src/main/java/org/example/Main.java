package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        int tokenMaxValue = 5;
        int playersNumber = 3;
        Bag bag = new Bag(tokenMaxValue);


        List<Player> players = new ArrayList<>();
        for(int i = 1; i <= playersNumber; i++)
        {
            players.add(new Player("Player " + i, bag, tokenMaxValue));
        }

        for (Player player : players) {
            player.start();
        }

        for (Player player : players) {
            player.join();
        }
 

    }
}