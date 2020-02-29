package com.desaerun.BlackJack;

import com.desaerun.Player;

public class BlackJackPlayer extends Player {
    protected BlackJackHand hand;

    public BlackJackPlayer(String name) {
        super(name);
        this.hand = new BlackJackHand(name + "'s hand");
    }

    public BlackJackHand getHand() {
        return this.hand;
    }
}
