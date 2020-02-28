package com.desaerun.BlackJack;

import com.desaerun.Hand;

import java.util.ArrayList;

public class BlackJackHand extends Hand {
    protected ArrayList<BlackJackCard> cards;

    public BlackJackHand(String label) {
        super(label);
    }

    public BlackJackHand(String label, BlackJackCard starting_card) {
        super(label);
        addCard(starting_card);
    }

    public BlackJackCard popCard(int i) {
        return cards.remove(i);
    }

    public BlackJackCard popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    public BlackJackCard getCard(int i) {
        return this.cards.get(i);
    }

    public int getValue() {
        int sum = 0;
        for (BlackJackCard card : cards) {
            if (card.getValue() == 11 && sum + card.getValue() > 21) {
                sum += 1;
            } else {
                sum += card.getValue();
            }
        }
        return sum;
    }
}
