package com.desaerun;

public class Deck extends CardCollection {
    public Deck(String label) {
        super(label);
    }

    public void deal(CardCollection target, int n) {
        for (int i = 0; i < n; i++) {
            Card card = popCard();
            target.addCard(card);
        }
    }

    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }
}
