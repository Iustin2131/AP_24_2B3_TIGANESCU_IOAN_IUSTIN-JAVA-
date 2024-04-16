package org.example;

import java.util.*;

public class Bag
{
    List<Token> tokens;

    public Bag(int tokenMaxValue)
    {
        tokens = new ArrayList<>();
        for(int i = 1; i <= tokenMaxValue; i++)
        {
            for(int  j = 1; j <= tokenMaxValue; j++)
            {
                if(i != j)
                    tokens.add(new Token(i,j));
            }
        }
        Collections.shuffle(tokens, new Random());
    }

    public synchronized Token extractToken() throws InterruptedException
    {
        Random rand = new Random();
        int randomIndex = rand.nextInt(tokens.size());
        Token token = tokens.remove(randomIndex);
        System.out.println(token);

        notifyAll();
        wait();
        return token;
    }


    @Override
    public String toString() {
        return "Bag{" +
                "tokens=" + tokens +
                '}';
    }
}