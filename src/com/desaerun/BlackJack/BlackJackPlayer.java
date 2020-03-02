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
}
