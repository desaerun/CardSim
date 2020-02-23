package com.desaerun;

public class Deck extends CardCollection {
    public Deck(String label) {
        super(label);
    }
    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }
}
