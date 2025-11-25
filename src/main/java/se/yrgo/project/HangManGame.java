package se.yrgo.project;

import java.util.List;
import java.util.Scanner;

public class HangManGame {

    public void welcome() {
        System.out.println("Welcome to the Hangman Game!\n");
        System.out.println("----------------------------------------");
        System.out.println("1. To start a new game, enter your name.");
        System.out.println("2. To end the game at any time, press 0.");
        System.out.println("----------------------------------------");
        System.out.println("Rules: ");
        System.out.println(" - Guess only one letter at a time, the program will tell you if it is correct or not.\n - Only letters from the english alphabet is acceptable.");
        System.out.println(" - For every guess your total guesses will increase by one, less is better!");
        System.out.println(" - For every wrong guess, you will lose one life, you got 10 lives to start with");
        System.out.println(" - The game will end if you lose all lives.");
        System.out.println(" - When the game ends you will receive your score, the lower the better!\n");
        System.out.println("GOOD LUCK!");

    }

    public List<Character> getWordList() {
        List<String> wordList = List.of("Java");
        Words word = new Words(wordList);

        String chosenWord = word.getRandomWord();

        List<Character> wordAsList = word.wordToLetters(chosenWord);

        return wordAsList;
    }

    public void startGame() {

        System.out.print("Please insert your name: ");

        try (Scanner input = new Scanner(System.in)) {
            String name = input.nextLine().trim();
            Player player = new Player(name);

            System.out.println("Welcome " + player.getName() + "!");

            System.out.print("Your word is: ");

            for (int i = 0; i <= getWordList().size(); i++) {
                System.out.print(" _ ");
            }
            System.out.print("\n\nLet's start, take a guess: ");
            Character letter = input.next().charAt(0);
            player.guess(letter);

            if (getWordList().contains(letter)) {
                System.out.println("Korrekt!");
            } else {
                System.out.println("Felaktigt!");
            }
        }

    }
}
