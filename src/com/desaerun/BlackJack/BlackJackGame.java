package com.desaerun.BlackJack;

import com.desaerun.Card;
import com.desaerun.CardGame;
import com.desaerun.Utilities.MenuIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.desaerun.Utilities.MenuIO.printMenuGetString;

public class BlackJackGame extends CardGame {
    public BlackJackDeck deck = new BlackJackDeck("Deckshoe", 6);
    private BlackJackPlayer dealer = new BlackJackPlayer("Dealer");

    private List<BlackJackPlayer> human_players = new ArrayList<>();
    private List<BlackJackPlayer> players = new ArrayList<>();

    public static List<BlackJackPlayer> getHumanPlayers(int min, int max) {
        List<BlackJackPlayer> human_players = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        List<String> menu_text = new ArrayList<>();
        menu_text.add("How many players (including yourself)[" + min + "-" + max + "]?: ");
        int n_players = MenuIO.printMenuGetInt(menu_text, min, max);
        for (int i = 2; i <= n_players; i++) {
            String player_name = printMenuGetString("What is player " + i + "'s name?: ");
            human_players.add(new BlackJackPlayer(player_name));
        }
        return human_players;
    }

    public void init() {
        System.out.println("Welcome to BlackJack!");
        deck.shuffle();

        //get the player's name and create a Player object
        human_players.add(new BlackJackPlayer(MenuIO.printMenuGetString("What is your name?: ")));
        human_players.addAll(getHumanPlayers(1, 6));

        players.addAll(human_players);
        players.add(dealer);
    }

    public void playerTurn(BlackJackPlayer player, BlackJackHand hand) {
        Card up_card = dealer.getHand().getCard(1);
        System.out.println("Dealer's hand: [X, " + up_card);
        System.out.print(player.getName() + "'s hand: ");
        hand.print();
        if (hand.getCard(0).getValue() == hand.getCard(1).getValue()) {
            char[] menu_options = {'y', 'n' };
            char option = MenuIO.printMenuGetChar("Would you like to split the hand?[Y/N]: ", menu_options);
            if (option == 'y') {
                BlackJackHand hand1 = new BlackJackHand("Split hand 1", hand.popCard(0));
                playerTurn(player, hand1);
                BlackJackHand hand2 = new BlackJackHand("Split hand 2", hand.popCard(0));
                playerTurn(player, hand2);
            }
        } else {
            int turn = 1;
            do {
                if (turn == 1) {
                    char[] menu_options = {'h', 's', 'd' };
                    char input_option = MenuIO.printMenuGetChar("Would you like to [H]it, [S]tand, or [D]ouble down?: ", menu_options);
                } else {
                    char[] menu_options = {'h', 's' };
                    char input_option = MenuIO.printMenuGetChar("Would you like to [H]it or [S]tand?: ", menu_options);
                }

                turn++;
            } while (hand.getValue() <= 21);
        }

    }

    public void dealerTurn(BlackJackPlayer dealer) {

    }

    public void play() {
        //everyone gets a card until the dealer has two cards
        for (int i = 0; i < 2; i++) {
            for (BlackJackPlayer player : players) {
                deck.deal(player.getHand(), 1);
            }
        }
        for (BlackJackPlayer player : players) {
            System.out.println(player.getName() + "'s hand: ");
            player.getHand().print();
        }
        for (BlackJackPlayer player : human_players) {
            playerTurn(player, player.getHand());
        }
        dealerTurn(dealer);
    }
}
