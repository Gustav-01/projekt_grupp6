package se.yrgo.project;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class HangManGame {

    public void welcome() {
        System.out.println("Welcome to the Hangman Game!\n");
        System.out.println("----------------------------------------");
        System.out.println("1. To start a new game, enter your name.");
        System.out.println("2. To end the game at any time, press 0.");
        System.out.println("----------------------------------------");
        System.out.println("Rules: ");
        System.out.println(" - Guess only one letter at a time, the program will tell you if it is correct or not.");
        System.out.println(" - Only letters from the english alphabet is acceptable.");
        System.out.println(" - For every guess your total guesses will increase by one, less is better!");
        System.out.println(" - For every wrong guess, you will lose one life, you got 10 lives to start with");
        System.out.println(" - The game will end if you lose all lives.");
        System.out.println(" - When the game ends you will receive your score, the lower the better!\n");
        System.out.println("GOOD LUCK!");
    }

    public void displayWord(List<Character> wordList, Set<Character> guessedLetters) {
        for (Character c : wordList) {
            if (guessedLetters.contains(Character.toLowerCase(c))) {
                System.out.print(c + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private boolean isWordSolved(List<Character> word, Set<Character> guesses) {
        for (char c : word) {
            if (!guesses.contains(Character.toLowerCase(c))) {
                return false;
            }
        }
        return true;
    }

    public List<Character> getWordList() {
        List<String> wordList = List.of("Java", "Python", "Maven", "Jenkins", "Gradle");
        Words word = new Words(wordList);
        String chosenWord = word.getRandomWord();
        return word.wordToLetters(chosenWord);
    }

    public void startGame() {
        System.out.print("Please insert your name: ");

        try (Scanner input = new Scanner(System.in)) {
            String name = input.nextLine().trim();
            Player player = new Player(name);

            List<Character> wordList = getWordList();
            Set<Character> guessedLetters = player.getGuesses();

            System.out.println("Welcome " + player.getName() + "!");
//            System.out.print("Your word is: ");
//            for (int i = 0; i < wordList.size(); i++) {
//                System.out.print(" _ ");
//            }
//            System.out.println("\n");

            while (!isWordSolved(wordList, guessedLetters) && player.getLives() > 0) {
                System.out.println("\nYour word is: ");
                displayWord(wordList, guessedLetters);
                System.out.print("\nTake a guess: ");
                char guess = input.next().toLowerCase().charAt(0);

                if (player.guess(guess)) {
                    if (wordList.contains(guess)) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Wrong!");
                        player.wrongGuess();
                    }
                } else {
                    System.out.println("You already guessed that letter!");
                }
            }

            if (isWordSolved(wordList, guessedLetters)) {
                System.out.println("You won!");
            } else {
                System.out.println("Game over! Ordet var: " + wordList);
            }
        }
    }

}