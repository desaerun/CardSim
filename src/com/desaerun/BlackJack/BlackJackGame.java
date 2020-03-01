package com.desaerun.BlackJack;

import com.desaerun.Card;
import com.desaerun.CardGame;
import com.desaerun.Utilities.MenuIO;

import java.util.ArrayList;
import java.util.List;

import static com.desaerun.Utilities.MenuIO.printMenuGetString;

public class BlackJackGame implements CardGame {
    private BlackJackDeck deck;
    private BlackJackPlayer dealer;

    private List<BlackJackPlayer> human_players = new ArrayList<>();
    private List<BlackJackPlayer> players = new ArrayList<>();

    public BlackJackGame() {
        super();
        this.deck = new BlackJackDeck("Deckshoe", 6);
        this.dealer = new BlackJackPlayer("Dealer");
    }

    public static List<BlackJackPlayer> getHumanPlayers(int min, int max) {
        List<BlackJackPlayer> human_players = new ArrayList<>();

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
        deck.print();
        deck.shuffle();
        deck.print();

        //get the player's name and create a Player object
        human_players.add(new BlackJackPlayer(MenuIO.printMenuGetString("What is your name?: ")));
        human_players.addAll(getHumanPlayers(1, 6));

        players.addAll(human_players);
        players.add(dealer);
    }

    public void playerTurn(BlackJackPlayer player, BlackJackHand hand) {
        Card up_card = dealer.getHand().getCard(1);
        System.out.println("Dealer's hand: [X, " + up_card + "]");
        System.out.print(player.getName() + "'s hand: ");
        hand.print();
        if (hand.getCard(0).getValue() == hand.getCard(1).getValue()) {
            char[] menu_options = {'y', 'n'};
            char option = MenuIO.printMenuGetChar("Would you like to split the hand?[Y/N]: ", menu_options);
            if (option == 'y') {
                BlackJackHand hand1 = new BlackJackHand("Split hand 1", hand.popCard(0));
                playerTurn(player, hand1);
                BlackJackHand hand2 = new BlackJackHand("Split hand 2", hand.popCard(0));
                playerTurn(player, hand2);
            }
        } else {
            boolean standing = false;
            int turn = 1;
            //this is the main player input loop
            do {
                if (player.getHand().getCard(0).getValue() == player.getHand().getCard(1).getValue()) {

                    standing = true;
                }
                if (turn == 1) {
                    char[] menu_options = {'h', 's', 'd'};
                    char input_option = MenuIO.printMenuGetChar("Would you like to [H]it, [S]tand, or [D]ouble down?: ", menu_options);
                    switch (input_option) {
                        case 'h':
                            deck.deal(player.getHand(), 1);
                            break;
                        case 's':
                            standing = true;
                            break;
                        case 'd':
                            deck.deal(player.getHand(), 1);
                            standing = true;
                            player.setWager(player.getWager() * 2);
                            break;
                    }
                } else {
                    char[] menu_options = {'h', 's'};
                    char input_option = MenuIO.printMenuGetChar("Would you like to [H]it or [S]tand?: ", menu_options);
                    switch (input_option) {
                        case 'h':
                            deck.deal(player.getHand(), 1);
                            break;
                        case 's':
                            standing = true;
                            break;
                        case 'd':
                            deck.deal(player.getHand(), 1);
                            standing = true;
                            player.adjustWager(player.getWager());
                            break;
                    }
                }
                turn++;
            } while (!standing);
            if (player.getHand().getValue() > 21) {
                System.out.println(player.getName() + " busts!");
            }
            if (player.getHand().isBlackJack()) {
                System.out.println("Blackjack!");
            }
        }

    }

    public void dealerTurn(BlackJackPlayer dealer) {
        //dealer logic goes here
    }

    public void play() {
        //everyone gets a card until the dealer has two cards
        for (int i = 0; i < 2; i++) {
            for (BlackJackPlayer player : players) {
                System.out.println("Dealing a card to player " + player.getName());
                deck.deal(player.getHand(), 1);
            }
        }
        if (!dealer.getHand().isBlackJack()) {
            System.out.println();
            System.out.print("Dealer's hand: ");
            BlackJackCard up_card = dealer.getHand().getCard(0);
            System.out.println("[X, " + up_card);
        } else {
            System.out.println();
            dealer.getHand().print();
            System.out.println("Dealer has Blackjack!");

        }
        for (BlackJackPlayer player : human_players) {
            System.out.println("Player " + player.getName() + "'s turn: ");
            if (!dealer.getHand().isBlackJack()) {
                playerTurn(player, player.getHand());
            }
        }
        dealerTurn(dealer);
    }
}
