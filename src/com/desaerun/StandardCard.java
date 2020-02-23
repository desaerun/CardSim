package com.desaerun;

public class StandardCard {
    public final Suit suit;
    public final Rank rank;

    public StandardCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
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
