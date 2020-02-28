package com.desaerun;

public class Player {
    private String name;
    protected Hand hand;
    private long wallet;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand(name + "'s hand");
    }

    public long getWallet() {
        return this.wallet;
    }

    public Hand getHand() {
        return this.hand;
    }

    public String getName() {
        return this.name;
    }

    public void adjustWallet(long n) {
        this.wallet += n;
    }

    public void printWallet() {
        System.out.println("Player " + this.name + " | $" + this.wallet);
    }
}
