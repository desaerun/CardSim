package com.desaerun.BlackJack;

import com.desaerun.Card;

public class BlackJackCard extends Card {
    public final Suit suit;
    public final Rank rank;

    public BlackJackCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getValue() {
        return this.rank.getValue();
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
        Two(2),
        Three(3),
        Four(4),
        Five(5),
        Six(6),
        Seven(7),
        Eight(8),
        Nine(9),
        Ten(10),
        Jack(10),
        Queen(10),
        King(10),
        Ace(11);
        int value;

        Rank(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
