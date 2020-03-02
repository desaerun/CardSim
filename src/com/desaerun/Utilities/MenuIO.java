package com.desaerun.Utilities;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuIO {
    public static int printMenuGetInt(List<String> menu_text, int min_allowed, int max_allowed) {
        Scanner s = new Scanner(System.in);
        int input = 0;
        boolean test;
        do {
            printMenuText(menu_text);
            try {
                input = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must enter a valid integer.");
                s.next();
            }
            test = (input >= min_allowed && input <= max_allowed);
        } while (!test);
        return input;
    }

    public static int printMenuGetInt(String menu_line, int min_allowed, int max_allowed) {
        Scanner s = new Scanner(System.in);
        int input = 0;
        boolean test;
        do {
            System.out.println(menu_line);
            try {
                input = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must enter a valid integer.");
                s.next();
            }
            test = (input >= min_allowed && input <= max_allowed);
        } while (!test);
        return input;
    }

    public static long printMenuGetLong(String menu_line, long min_allowed, long max_allowed) {
        Scanner s = new Scanner(System.in);
        long input = 0;
        boolean test;
        do {
            System.out.println(menu_line);
            try {
                input = s.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("You must enter a valid integer.");
                s.next();
            }
            test = (input >= min_allowed && input <= max_allowed);
        } while (!test);
        return input;
    }

    public static char printMenuGetChar(List<String> menu_text, char[] allowed) {
        Scanner s = new Scanner(System.in);
        char c;
        boolean test = false;
        do {
            printMenuText(menu_text);
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

    public static char printMenuGetChar(String menu_line, char[] allowed) {
        Scanner s = new Scanner(System.in);
        char c;
        boolean test = false;
        do {
            System.out.println(menu_line);
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

    public static String printMenuGetString(List<String> menu_text) {
        Scanner s = new Scanner(System.in);
        printMenuText(menu_text);
        return s.nextLine();
    }

    public static String printMenuGetString(String menu_line) {
        Scanner s = new Scanner(System.in);
        System.out.println(menu_line);
        return s.nextLine();
    }

    public static void printMenuText(List<String> menu_text) {
        for (String menu_line : menu_text) {
            System.out.println(menu_line);
        }
    }
}
