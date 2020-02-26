package com.desaerun;

import com.desaerun.BlackJack.BlackJackDeck;

public class Main {
    public static void main(String[] args) {
        //get the player's name and create a Player object

        // while not 'exit'
        // ask them which game they want to play

        //hold'em
        //blackjack
        //crazy 8's
        //play game
        Player p1 = new Player("desaerun");
        p1.printWallet();
        Deck d1 = new BlackJackDeck("test");
        d1.print();
        Hand h1 = new Hand("hand 1");
        d1.deal(h1, 7);
        System.out.println("Hand 1:");
        h1.print();
    }
}
