package com.desaerun.BlackJack;

import com.desaerun.Card;
import com.desaerun.CardCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackDeck extends CardCollection {
    public List<BlackJackCard> cards;

    public BlackJackDeck(String label, int n_decks) {
        super(label);
        cards = new ArrayList<>();
        for (int i = 0; i < n_decks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    this.cards.add(new BlackJackCard(suit, rank));
                }
            }
        }
    }

    public BlackJackCard popCard(int i) {
        return cards.remove(i);
    }

    public BlackJackCard popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    public void deal(BlackJackHand target, int n) {
        for (int i = 0; i < n; i++) {
            BlackJackCard card = popCard();
            target.addCard(card);
        }
    }

    public BlackJackCard lastCard() {
        return this.cards.get(cards.size() - 1);
    }

    public BlackJackCard getCard(int i) {
        return this.cards.get(i);
    }

    public int size() {
        return this.cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void print() {
        for (BlackJackCard card : this.cards) {
            System.out.println(card);
        }
    }
}
