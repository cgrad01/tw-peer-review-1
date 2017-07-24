package com.thoughtworks.tw101.exercises.exercise8;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Duvall_Lanell on 7/4/17.
 */

// Identify two dispensers, two bloaters and one OO abuser
public class Guess {
    int guessNumber;
    ArrayList<Integer> guessAttempts = new ArrayList<Integer>();
    Random rand = new Random();
    boolean gameOver = false;

    public Guess (int max, int min) {
        guessNumber = rand.nextInt(max) + min;
    }

//    refactored to remove unneeded temp (dispensable)
    private void checkGuess(int guess) {
        guessAttempts.add(guess);
        if (guess == guessNumber) {
            showCorrect(guess);
        } else {
            evaluateMiss(guess);
        }
    }


    public void getGuess(Scanner scan) {
        try {
            checkGuess(scan.nextInt());
            while (!gameOver) {
                System.out.print("Guess the number between 1 and 100: ");
                checkGuess(scan.nextInt());
            }
        } catch (NumberFormatException e) {
            System.out.println("Number was not entered. Could not parse String");
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input. Expecting a number.");
        }

    }

//    extracted this from checkGuess() (dispenseable)
//    simplified conditional (oo abuser)
    private void evaluateMiss(int guess) {
        String missDirection = "Low\n";
        if (guess > guessNumber) {
            missDirection = "High.\n";
        }
        System.out.println(guess + " is too " + missDirection);
    }

//    extracted this method from getGuess() (bloater)
    public Scanner promptGuess(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Guess the number between 1 and 100: ");
        return scan;
    }

//    extracted this method from checkGuess() (bloater)
    public void showCorrect(int guess) {
        gameOver = true;
        System.out.println("You guessed " + guess + ". Correct!!!!!!\n");
        System.out.println("Your guesses were: ");

        for (int num : guessAttempts) {
            System.out.print(num + " ");
        }
    }
}
