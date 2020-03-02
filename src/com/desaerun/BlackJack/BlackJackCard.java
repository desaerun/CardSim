package com.desaerun.BlackJack;

import com.desaerun.Card;

public class BlackJackCard extends Card {
    public BlackJackCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    public int getValue() {
        int pos = rank.ordinal() + 2;
        if (pos == 14) { //ace
            return 11;
        } else if (pos >= 10 && pos <= 13) {
            return 10;
        } else {
            return pos;
        }
    }
}
