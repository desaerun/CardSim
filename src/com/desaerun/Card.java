package com.desaerun;

public class Card {
    public final Suit suit;
    public final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        throw new UnsupportedOperationException();
    }

    public Rank getRank() {
        return this.rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }

    public enum Suit {
        Spades,
        Hearts,
        Clubs,
        Diamonds
    }

    public enum Rank {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace
    }
}
