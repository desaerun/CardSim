package com.desaerun.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackDeck {
    protected List<BlackJackCard> cards;
    private String label;

    public BlackJackDeck(String label, int number_decks) {
        this.label = label;
        this.cards = new ArrayList<>();
        for (int i = 1; i <= number_decks; i++) {
            for (BlackJackCard.Suit suit : BlackJackCard.Suit.values()) {
                for (BlackJackCard.Rank rank : BlackJackCard.Rank.values()) {
                    cards.add(new BlackJackCard(suit, rank));
                }
            }
        }
    }

    public void deal(BlackJackHand target, int n) {
        for (int i = 0; i < n; i++) {
            BlackJackCard card = popCard();
            target.addCard(card);
        }
    }

    public BlackJackCard popCard(int i) {
        return cards.remove(i);
    }

    public BlackJackCard popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    public int size() {
        return cards.size();
    }

    public BlackJackCard getCard(int i) {
        return this.cards.get(i);
    }
}
