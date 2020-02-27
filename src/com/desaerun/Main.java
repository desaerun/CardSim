package com.desaerun;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static char printMenuGetChar(List<String> menu_text, char[] allowed) {
        Scanner s = new Scanner(System.in);
        char c;
        boolean test = false;
        do {
            for (String menu_line : menu_text) {
                System.out.println(menu_line);
            }
            String input = s.nextLine();
            c = input.charAt(0);
            for (char cur_char : allowed) {
                cur_char = Character.toLowerCase(cur_char);
                if (c == cur_char) {
                    test = true;
                    break;
                }
            }
        } while (!test);
        return c;
    }

    public static int printMenuGetInt(List<String> menu_text, int min_allowed, int max_allowed) {
        Scanner s = new Scanner(System.in);
        int input;
        boolean test = false;
        do {
            for (String menu_line : menu_text) {
                System.out.println(menu_line);
            }
            input = s.nextInt();
            test = (input >= min_allowed && input <= max_allowed);
        } while (!test);
        return input;
    }

    public static void main(String[] args) {
        List<Player> human_players = new ArrayList<>();

        //get the player's name and create a Player object
        System.out.println("What is your name?: ");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        human_players.add(new Player(name));

        final List<String> menu_text = new ArrayList<>();
        menu_text.add("Which game would you like to play?: ");
        menu_text.add("1. BlackJack");
        menu_text.add("2. Hold'em");
        menu_text.add("[Q] to quit.");

        final char[] menu_choices = {'1', 'q'};

        char menu_choice = printMenuGetChar(menu_text, menu_choices);

        switch (menu_choice) {
            case '1':
                //blackjack
                System.out.println("How many human players?: ");
                int human_players_count_input = printMenuGetInt("How many human players?: ", 1, 6);


                break;
            case '2':
                //hold'em
                break;
            case 'q':
        }
        // while not 'exit'
        // ask them which game they want to play

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
