package com.desaerun.BlackJack;

import com.desaerun.Deck;

public class BlackJackDeck extends Deck {

    public BlackJackDeck(String label, int number_decks) {
        super(label);
        for (int i = 1; i <= number_decks; i++) {
            for (BlackJackCard.Suit suit : BlackJackCard.Suit.values()) {
                for (BlackJackCard.Rank rank : BlackJackCard.Rank.values()) {
                    cards.add(new BlackJackCard(suit, rank));
                }
            }
        }
    }

    public BlackJackDeck() {
        super("default");
    }
}
