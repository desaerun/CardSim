package com.desaerun;

import com.desaerun.BlackJack.BlackJackGame;
import com.desaerun.Utilities.MenuIO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> human_players = new ArrayList<>();

        //get the player's name and create a Player object
        human_players.add(new Player(MenuIO.printMenuGetString("What is your name?: ")));

        // while not 'exit'
        // ask them which game they want to play
        List<String> menu_text = new ArrayList<>();
        menu_text.add("Which game would you like to play?: ");
        menu_text.add("1. BlackJack");
        menu_text.add("2. Hold'em");
        menu_text.add("[Q] to quit.");

        final char[] menu_choices = {'1', 'q'};


        char menu_choice = MenuIO.printMenuGetChar(menu_text, menu_choices);
        menu_text.clear();

        switch (menu_choice) {
            case '1':
                //blackjack
                BlackJackGame blackjack = new BlackJackGame();
                blackjack.init();
                blackjack.play(human_players);
                break;
            case '2':
                //hold'em
                break;
            case 'q':
        }

        //hold'em
        //blackjack
        //crazy 8's

        //play game
/*
        p1.printWallet();
        Deck d1 = new BlackJackDeck("test");
        d1.print();
        Hand h1 = new Hand("hand 1");
        d1.deal(h1, 7);
        System.out.println("Hand 1:");
        h1.print();
 */
    }
}
