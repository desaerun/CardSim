package com.desaerun;

public class Player {
    private String name;
    private Hand hand;
    private long wallet;

    public Player(String name) {
        this.name = name;
    }

    public long getWallet() {
        return this.wallet;
    }

    public void adjustWallet(long n) {
        this.wallet += n;
    }

    public void printWallet() {
        System.out.println("Player " + this.name + " | $" + this.wallet);
    }
}
