package com.desaerun;

public class Player {
    protected Hand hand;
    private String name;
    private long wallet;
    private long wager;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand(name + "'s hand");
    }

    public Hand getHand() {
        return this.hand;
    }

    public String getName() {
        return this.name;
    }

    public long getWallet() {
        return this.wallet;
    }

    public void setWallet(long n) {
        this.wallet = n;
    }

    public void adjustWallet(long n) {
        this.wallet += n;
    }

    public void printWallet() {
        System.out.println("Player " + this.name + " | $" + this.wallet);
    }

    public long getWager() {
        return this.wager;
    }

    public void setWager(long wager) {
        this.wager = wager;
    }

    public void adjustWager(long wager) {
        this.wager += wager;
    }
}
