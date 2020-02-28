package com.desaerun.BlackJack;

import com.desaerun.Player;

public class BlackJackPlayer extends Player {
    protected BlackJackHand hand;

    public BlackJackPlayer(String label) {
        super(label);
    }

    public BlackJackHand getHand() {
        return this.hand;
    }
}
