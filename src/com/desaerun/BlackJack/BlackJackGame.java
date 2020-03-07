package com.desaerun.BlackJack;

import com.desaerun.CardGame;
import com.desaerun.Utilities.MenuIO;

import java.util.ArrayList;
import java.util.List;

import static com.desaerun.Utilities.MenuIO.printMenuGetChar;
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
        deck.shuffle();

        //get the player's name and create a Player object
        human_players.add(new BlackJackPlayer(MenuIO.printMenuGetString("What is your name?: ")));
        human_players.addAll(getHumanPlayers(1, 6));

        players.addAll(human_players);
        players.add(dealer);
    }

    public void playerTurn(BlackJackPlayer player, BlackJackHand hand) {
        hand.setWager(player.getWager());


        if (hand.getCard(0).getRank() == hand.getCard(1).getRank()) {
            System.out.println("--------------------------------------------------------------");
            dealer.printDealerHand();
            hand.print();
            char[] menu_options = {'y', 'n'};
            char option = MenuIO.printMenuGetChar("Would you like to split the hand?[Y/N]: ", menu_options);
            if (option == 'y') {
                for (int i = 0; i < 2; i++) {
                    int hand_index = player.num_hands();
                    BlackJackHand split_hand = new BlackJackHand(player.getName() + " split hand " + hand_index, hand.popCard(0));
                    deck.deal(split_hand, 1);
                    player.addHand(split_hand);
                    playerTurn(player, split_hand);
                }
                player.removeHand(0);
            }
        } else {
            boolean standing = false;
            int turn = 1;
            //this is the main player input loop
            do {
                System.out.println("--------------------------------------------------------------");
                dealer.printDealerHand();
                hand.print();
                if (hand.getCard(0).getValue() + hand.getCard(1).getValue() == 21) { // if the player has blackjack
                    standing = true;
                    player.adjustWager((long) Math.floor(player.getWager() * .5));
                } else {
                    if (turn == 1) {
                        char[] menu_options = {'h', 's', 'd'};
                        char input_option = MenuIO.printMenuGetChar("Would you like to [H]it, [S]tand, or [D]ouble down?: ", menu_options);
                        switch (input_option) {
                            case 'h':
                                deck.deal(hand, 1);
                                break;
                            case 's':
                                standing = true;
                                break;
                            case 'd':
                                if (hand.getWager() * 2 <= player.getWallet()) {
                                    deck.deal(hand, 1);
                                    standing = true;
                                    hand.setWager(hand.getWager() * 2);
                                } else {
                                    if (printMenuGetChar("You do not have enough funds to double down completely, double down for less?[Y/N]:", new char[]{'y', 'n'}) == 'y') {
                                        deck.deal(hand, 1);
                                        standing = true;
                                        hand.setWager(player.getWallet());
                                    }
                                }
                                break;
                        }
                    } else {
                        char[] menu_options = {'h', 's'};
                        char input_option = MenuIO.printMenuGetChar("Would you like to [H]it or [S]tand?: ", menu_options);
                        switch (input_option) {
                            case 'h':
                                deck.deal(hand, 1);
                                break;
                            case 's':
                                standing = true;
                                break;
                            case 'd':
                                deck.deal(hand, 1);
                                standing = true;
                                player.adjustWager(player.getWager());
                                break;
                        }
                    }
                }
                turn++;
            } while (!standing && hand.getValue() <= 21);
            if (hand.getValue() > 21) {
                System.out.println(player.getName() + " busts!");
            }
            if (hand.getValue() == 21 && !hand.isBlackJack()) {
                System.out.println("21!");
            }
            hand.print();
        }
        if (hand.isBlackJack()) {
            System.out.println("Blackjack!");
            hand.print();
        }
    }

    public void dealerTurn(BlackJackPlayer dealer) {
        //dealer logic goes here
        System.out.println("The dealer takes her turn: ");
        dealer.printHand();
        while (dealer.getHand().getValue() <= 16 || (dealer.getHand().isSoft17())) {
            System.out.print("Dealer hits: ");
            deck.deal(dealer.getHand(), 1);
            dealer.printHand();
        }
        if (dealer.getHand().getValue() < 21) {
            System.out.println("Dealer stands.");
        }
    }

    public void score(BlackJackPlayer dealer, List<BlackJackPlayer> human_players) {
        if (dealer.getHand().getValue() > 21) {
            System.out.println("Dealer busts!");
        }
        for (BlackJackPlayer player : human_players) {
            for (BlackJackHand hand : player.getHands()) {
                dealer.printHand();
                hand.print();
                if (dealer.getHand().isBlackJack()) {
                    if (hand.getValue() == 21) {
                        System.out.println("Push! Player " + player.getName() + " receives their wager of $" + hand.getWager() + " back!");
                    } else {
                        System.out.println("Dealer wins! Player " + player.getName() + " loses $" + hand.getWager() + "!");
                        player.adjustWallet(-hand.getWager());
                    }
                }
                if (hand.isBlackJack()) {  // player had blackjack
                    System.out.println("Player " + player.getName() + "has Blackjack! Player " + player.getName() + " wins 1.5x their wager ($" + (long) Math.floor(hand.getWager() * 1.5) + ")!");
                    player.adjustWallet((long) Math.floor(hand.getWager() * 1.5));
                } else if (dealer.getHand().getValue() > 21 && hand.getValue() <= 21) { // dealer busts and player didn't bust
                    System.out.println("Dealer busts! Player " + player.getName() + " wins $" + hand.getWager() + "!");
                    player.adjustWallet(hand.getWager());
                } else if (hand.getValue() > dealer.getHand().getValue() && hand.getValue() <= 21) {  //player didn't bust, and beat the dealer
                    System.out.println("Player " + player.getName() + " beat the dealer! " + player.getName() + " wins $" + hand.getWager() + "!");
                    player.adjustWallet(hand.getWager());
                } else if (hand.getValue() > 21) { // player busted
                    System.out.println("Player " + player.getName() + " busts! Player " + player.getName() + " loses $" + hand.getWager() + "!");
                    player.adjustWallet(-hand.getWager());
                } else if (dealer.getHand().getValue() > hand.getValue() && dealer.getHand().getValue() <= 21) { // dealer beat the player and didn't bust
                    System.out.println("Dealer wins! Player " + player.getName() + " loses $" + hand.getWager() + "!");
                    player.adjustWallet(-hand.getWager());
                } else if (hand.getValue() == dealer.getHand().getValue()) {  //players push
                    System.out.println("Push! Player " + player.getName() + " receives their wager of $" + hand.getWager() + " back!");
                } else {
                    System.out.println("Some weird shit happened.");
                }
                System.out.println("--------------------------------------------------------------");
                System.out.println(player.getName() + "'s balance: $" + player.getWallet());
            }
            System.out.println("--------------------------------------------------------------");
            player.resetHands();
        }
        dealer.getHand().reset();
    }

    public void play() {
        do {
            for (BlackJackPlayer player : human_players) {
                if (player.getWallet() > 0) {
                    long wager = MenuIO.printMenuGetLong(player.getName() + ", how much would you like to wager?: [$1-$" + player.getWallet() + "]", 1, player.getWallet());
                    player.setWager(wager);
                }
            }
            //everyone gets a card until the dealer has two cards
            for (int i = 0; i < 2; i++) {
                for (BlackJackPlayer player : players) {
                    if (player.getWallet() > 0) {
                        deck.deal(player.getHand(0), 1);
                    }
                }
            }
            if (!dealer.getHand().isBlackJack()) { // if the dealer doesn't have blackjack
                System.out.println();
                System.out.print("Dealer's hand: ");
                BlackJackCard up_card = dealer.getHand(0).getCard(1);
                System.out.println("[X, " + up_card + "] [" + up_card.getValue() + "]");
                for (BlackJackPlayer player : human_players) {
                    if (player.getWallet() > 0) {
                        System.out.println("==============================================================");
                        System.out.println("Player " + player.getName() + "'s turn: ");
                        if (!dealer.getHand(0).isBlackJack()) {
                            playerTurn(player, player.getHand(0));
                        }
                    }
                }
                dealerTurn(dealer);
            } else { // if dealer has blackjack
                dealer.printHand();
                System.out.println("Dealer has Blackjack!");
                /*                for (BlackJackPlayer player : human_players) {
                    for (BlackJackHand hand : player.getHands()) {
                        if (!hand.isBlackJack()) { //if dealer has blackjack but the player doesn't, player automatically loses
                            System.out.println("Player " + player.getName() + " loses $" + player.getWager() + "!");
                            player.adjustWallet(-player.getWager());
                        } else { // if dealer has blackjack but so does the player, it's a push
                            System.out.println("Player " + player.getName() + " pushes! You get your wager of $" + player.getWager() + " back!");
                        }
                    }
                }*/
            }
            System.out.println("==============================================================");
            System.out.println("==============================================================");
            score(dealer, human_players);
            for (BlackJackPlayer player : human_players) {
                if (player.getWallet() <= 0) {
                    System.out.println(player.getName() + " is out of funds!");
                }
            }
        } while (printMenuGetChar("Would you like to play again?[Y/N]", new char[]{'Y', 'N'}) != 'n');
    }
}