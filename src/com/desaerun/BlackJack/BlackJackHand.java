package com.desaerun.BlackJack;

import com.desaerun.Hand;

import java.util.ArrayList;

public class BlackJackHand extends Hand {
    public ArrayList<BlackJackCard> cards;

    public BlackJackHand(String label) {
        super(label);
        cards = new ArrayList<>();

    }

    public BlackJackHand(String label, BlackJackCard starting_card) {
        super(label);
        cards = new ArrayList<>();
        addCard(starting_card);
    }

    public BlackJackCard popCard(int i) {
        return cards.remove(i);
    }

    public BlackJackCard popCard() {
        int i = size() - 1;
        return popCard(i);
    }

    public void addCard(BlackJackCard card) {
        this.cards.add(card);
    }

    public BlackJackCard getCard(int i) {
        System.out.println("inside getCard method of BlackJackHand, this.cards is: ");
        return cards.get(i);
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

    public void display() {
        System.out.println(getLabel() + ": ");
        for (int i = 0; i < size(); i++) {
            System.out.println(getCard(i));
        }
    }

    public int size() {
        return this.cards.size();
    }
}
