package org.example;

public class Token {
    private int first;
    private int second;

    public Token(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Token{ " +
                "first=" + first +
                ", second=" + second +
                " }";
    }
}
