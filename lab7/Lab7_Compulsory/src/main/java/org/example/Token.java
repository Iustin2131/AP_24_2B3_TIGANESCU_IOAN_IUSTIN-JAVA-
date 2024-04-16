package org.example;

public class Token
{
    private int first;
    private int second;
    public Token(int first, int second)
    {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Token{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
