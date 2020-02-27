package com.desaerun.BlackJack;

import com.desaerun.Deck;

public class BlackJackDeck extends Deck {
    public static final int number_decks = 4;

    public BlackJackDeck(String label) {
        super(label);
        for (int i = 1; i <= number_decks; i++) {
            System.out.println("Generating deck " + i);
            for (BlackJackCard.Suit suit : BlackJackCard.Suit.values()) {
                System.out.println("Iterating suit " + suit);
                for (BlackJackCard.Rank rank : BlackJackCard.Rank.values()) {
                    System.out.println("[" + rank + "]");
                    cards.add(new BlackJackCard(suit, rank));
                }
            }
        }
    }
}
