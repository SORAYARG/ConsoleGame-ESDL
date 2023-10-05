package util;

import java.util.Scanner;
import java.util.Random;

public class Util {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static String fixStringLength(int length, String word) {
        if (word.length() >= length) {
            return word.substring(0, length);
        } else {
            int difference = length - word.length();
            if (difference % 2 != 0) {
                word = " " + word;
                difference--;
            }
            difference /= 2;
            return " ".repeat(difference) + word + " ".repeat(difference);
        }
    }

    public static int rollDiceNTimesGreaterNumber(int times, int min, int max, String message) {
        System.out.println("\n     ******* " + message);
        int maxResult = 0;
        for (int i = 1; i <= times; i++) {
            requestEnter("     Press enter to roll the dice, time: " + i + "\n");
            int result = generateRandomNumber(min, max);
            System.out.println("          The result is: " + result);
            maxResult = Math.max(maxResult, result);
        }
        System.out.println("     The highest number obtained was: " + maxResult);
        requestEnter("     Press enter to continue");

        return maxResult;
    }

    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static String requestString(String message) {
        String response = "";
        boolean invalidInput = false;

        do {
            System.out.println("\n" + message);
            response = scanner.nextLine().trim();
            if (!response.isEmpty()) {
                invalidInput = false;
            } else {
                invalidInput = true;
                System.out.println("Please enter at least one character.");
            }

        } while (invalidInput);

        return response;
    }

    public static String requestEnter(String message) {
        System.out.println("\n" + message);
        return scanner.nextLine();
    }
}
