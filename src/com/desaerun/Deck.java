package com.desaerun;

public class Deck extends CardCollection {
    public Deck(String label) {
        super(label);
        int number_decks = 1;
        for (int i = 1; i <= number_decks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
    }

    public Deck(String label, int n_decks) {
        super(label);
        for (int i = 1; i <= n_decks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
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
