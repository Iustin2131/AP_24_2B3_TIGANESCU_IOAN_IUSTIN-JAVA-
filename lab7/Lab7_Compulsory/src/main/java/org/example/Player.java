package org.example;

public class Player extends Thread
{
    String name;
    private int tokenMaxValue;
    private Bag bag;

    public Player(String name, Bag bag, int tokenMaxValue)
    {
        this.name = name;
        this.bag = bag;
        this.tokenMaxValue = tokenMaxValue;
    }

    @Override
    public void run()
    {
        while(!bag.tokens.isEmpty())
        {
            synchronized (bag) {
                System.out.print(name + " got token : ");
                try {
                    Token token = bag.extractToken();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(bag.tokens.isEmpty())
                System.exit(0);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}