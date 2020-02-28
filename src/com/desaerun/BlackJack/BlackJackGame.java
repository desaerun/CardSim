package com.desaerun.BlackJack;

import com.desaerun.CardGame;
import com.desaerun.Player;

import java.util.List;

public class BlackJackGame extends CardGame {
    public void init() {
        System.out.println("Welcome to BlackJack!");

        BlackJackDeck deck = new BlackJackDeck("Deckshoe", 6);
    }

    public void play(List<Player> players) {
        Player dealer = new Player("Dealer");
        List<Player> human_players = getHumanPlayers();
        /*
        //fix this
        deck.deal(players[i].getHand(), 1);
         */
    }
}
