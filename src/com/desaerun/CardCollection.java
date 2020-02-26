package com.desaerun;

import java.util.ArrayList;
import java.util.Collections;

public class CardCollection {
    protected ArrayList<Card> cards;
    private String label;

    public CardCollection(String label) {
        this.label = label;
        this.cards = new ArrayList<>();
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public String getLabel() {
        return this.label;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card popCard(int i) {
        return cards.remove(i);
    }

    public Card popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    public Card lastCard() {
        return cards.get(cards.size() - 1);
    }

    public boolean empty() {
        return cards.size() == 0;
    }

    public void swapCards(int i, int j) {
        Card temp = cards.get(i);
        cards.set(i, cards.get(j));
        cards.set(j, temp);
    }

    public void print() {
        for (Card card : this.cards) {
            System.out.println(card);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }
}
