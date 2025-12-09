package se.yrgo.project;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * HangManGame, a classic fun game.
 * It handles word selection, user guesses, displaying the word progress, and tracking wrong guesses.
 */
public class HangManGame {

    /**
     * Prints a welcome message and game rules.
     */
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

    /**
     * Displays the word on the console with guessed letters revealed and underscores for unguessed letters.
     *
     * @param wordList the word as a list of characters.
     * @param guessedLetters the letters the player has guessed so far.
     */
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

    /**
     * Checks if the word has been completely guessed.
     *
     * @param word the word as a list of characters.
     * @param guesses the letters the player has guessed.
     * @return true if all letters in the word have been guessed, false otherwise.
     */
    public boolean isWordSolved(List<Character> word, Set<Character> guesses) {
        for (char c : word) {
            if (!guesses.contains(Character.toLowerCase(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Selects a word from a premade list and converts it into a list of characters.
     *
     * @return the chosen word as a list of characters.
     */
    public List<Character> getWordList() {
        List<String> wordList = List.of("Java", "Python", "Jenkins", "Groovy", "Maven");
        Words word = new Words(wordList);
        String chosenWord = word.getRandomWord();
        return word.wordToLetters(chosenWord);
    }

    /**
     * Starts the Hangman game, interacting with the player until the game ends or the player exits.
     * Handles input, checking guesses, and displaying game progress and results.
     */
    public void startGame() {
        System.out.print("Please insert your name: ");

        try (Scanner input = new Scanner(System.in)) {
            String name = input.nextLine().trim();
            if(name.equals("0")){
                System.exit(0);
            }
            Player player = new Player(name,10);

            List<Character> wordList = getWordList();
            Set<Character> guessedLetters = player.getGuesses();

            System.out.println("Welcome " + player.getName() + "!");
            System.out.print("Your word is: ");
            for (int i = 0; i < wordList.size(); i++) {
                System.out.print(" _ ");
            }
            System.out.println("\n");

            while (!isWordSolved(wordList, guessedLetters) && player.getLives() > 0) {
                displayWord(wordList, guessedLetters);
                System.out.print("Take a guess: ");
                char guess = input.next().toLowerCase().charAt(0);
                if(guess == '0'){
                    break;
                }

                if (player.guess(guess)) {
                    if (wordList.contains(guess)) {
                        System.out.println("Correct!\n");
                    } else {
                        System.out.println("Wrong!\n");
                        player.wrongGuess(guess);
                    }
                } else {
                    System.out.println("You already guessed that letter!");
                }
                System.out.println("Your wrong guesses: " + player.getWrongGuesses());
                System.out.println("Lives remaining: " + player.getLives());
                System.out.println();
            }

            if (isWordSolved(wordList, guessedLetters)) {
                System.out.println("YOU WON!");
            } else {
                System.out.println("\nGAME OVER! The man has been hanged...\nThe word was: " + wordList);
                System.out.println();
            }

            System.out.println("You guessed wrong [" + player.getWrongGuesses().size() + "] times!");
        }
    }

}
