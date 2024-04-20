package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bag {
    private List<Token> tokens;
    private final Lock lock = new ReentrantLock();

    public Bag(int tokenMaxValue) {
        tokens = new ArrayList<>();
        for (int i = 1; i <= tokenMaxValue; i++) {
            for (int j = 1; j <= tokenMaxValue; j++) {
                if (i != j)
                    tokens.add(new Token(i, j));
            }
        }
        Collections.shuffle(tokens, new Random());
    }

    public Token extractToken() throws InterruptedException {
        lock.lock();
        try {
            if (tokens.isEmpty()) {
                return null;
            }
            Random rand = new Random();
            int randomIndex = rand.nextInt(tokens.size());
            Token token = tokens.remove(randomIndex);
            return token;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Bag{" +
                "tokens=" + tokens +
                '}';
    }
}



