package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends Thread {
    private String name;
    private Bag bag;
    private List<Token> extractedTokens;
    private AtomicBoolean gameInProgress;
    private long lastTokenExtractionTime;

    public long getLastTokenExtractionTime() {
        return lastTokenExtractionTime;
    }

    public void setLastTokenExtractionTime(long lastTokenExtractionTime) {
        this.lastTokenExtractionTime = lastTokenExtractionTime;
    }

    public Player(String name, Bag bag, AtomicBoolean gameInProgress) {
        this.name = name;
        this.bag = bag;
        this.extractedTokens = new ArrayList<>();
        this.gameInProgress = gameInProgress;
    }

    @Override
    public void run() {
        try {
            while (gameInProgress.get()) {
                Token token = bag.extractToken();
                if (token == null) {
                    break;
                }
                extractedTokens.add(token);
                lastTokenExtractionTime = System.currentTimeMillis();
                System.out.println(name + " got token : " + token);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Token> getExtractedTokens() {
        return extractedTokens;
    }


    @Override
    public String toString() {
        return name;
    }
}
