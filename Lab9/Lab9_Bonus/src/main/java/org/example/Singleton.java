package org.example;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
