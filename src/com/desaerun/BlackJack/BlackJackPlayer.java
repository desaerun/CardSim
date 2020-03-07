package com.desaerun.BlackJack;

import com.desaerun.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackJackPlayer extends Player {
    private List<BlackJackHand> hands;
    private String name;

    public BlackJackPlayer(String name) {
        super(name);
        this.hands = new ArrayList<>();
        this.name = name;
        this.hands.add(new BlackJackHand(name + "'s hand"));
    }

    public void addHand(BlackJackHand hand) {
        this.hands.add(hand);
    }

    public void removeHand(int i) {
        this.hands.remove(i);
    }

    public BlackJackHand getHand(int i) {
        return this.hands.get(i);
    }

    public BlackJackHand getHand() {
        return this.hands.get(0);
    }

    public List<BlackJackHand> getHands() {
        return this.hands;
    }

    public int num_hands() {
        return this.hands.size();
    }

    public void resetHands() {
        this.hands = new ArrayList<>();
        this.hands.add(new BlackJackHand(name + "'s hand"));
    }

    public void printHand(int i) {
        hands.get(i).print();
    }

    public void printHand() {
        printHand(0);
    }

    public void printDealerHand(boolean hide_downcard) {
        if (hide_downcard) {
            if (getHand(0).size() > 0) {
                BlackJackCard up_card = getHand(0).getCard(1);
                System.out.println("Dealer's hand: [X, " + up_card + "] [" + up_card.getValue() + "]");
            }
        } else {
            getHand(0).print();
        }
    }

    public void printDealerHand() {
        printDealerHand(true);
    }
}
