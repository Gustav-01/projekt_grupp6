package se.yrgo.project;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Player} in the Hangman game.
 * Keeps track of the player's name, remaining lives, guessed letters, wrong guesses, and score.
 * Lives decrease on wrong guesses, and correct guesses increase the score.
 * All guessed letters are stored to prevent repeated guesses.
 * <p>
 * Use {@link #correctGuess()} and {@link #wrongGuess(Character)} to update the game state.
 */

public class Player {
    private final String name;
    private int lives;
    private final Set<Character> guesses;
    private final Set<Character> wrongGuesses;
    private int score;

    /**
     * Creates a new player with a given name and number of lives.
     *
     * @param name  the name of the player, cannot be null
     * @param lives starting number of lives (must be greater than 0)
     * @throws IllegalArgumentException if name is null or lives is 0 or less than 0.
     */
    public Player(String name, int lives) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be null or empty.");
        }
        if (lives <= 0) {
            throw new IllegalArgumentException("Lives must be greater than 0.\n");
        }
        this.name = name;
        this.lives = lives;
        this.guesses = new HashSet<>();
        this.wrongGuesses = new HashSet<>();
        this.score = 0;
    }

    /**
     * Records a guessed letter.
     * <p>
     * Converts the letter to lowercase, checks if it's a valid English letter,
     * and stores it if it hasn't been guessed before.
     *
     * @param letter the letter being guessed, must not be null
     * @return true if this letter was not guessed before, false if it was already guessed
     * @throws IllegalArgumentException if the letter is null or not an English alphabet letter
     */
    public boolean guess(Character letter) {
        if (letter == null) {
            throw new IllegalArgumentException("Guessed letter cannot be null.");
        }
        letter = Character.toLowerCase(letter);


        if (!Character.isLetter(letter) || letter < 'a' || letter > 'z') {
            throw new IllegalArgumentException("Only English alphabet letters from A-Z are allowed.");
        }
        if (guesses.contains(letter)) {
            return false;
        } else {
            guesses.add(letter);
            return true;
        }
    }

    /**
     * Marks a guess as wrong and decreases lives by 1.
     *
     * @param letter the incorrect guessed letter, must not be null
     * @throws IllegalArgumentException if letter is null
     * @throws IllegalStateException    if the player has no lives left
     */
    public void wrongGuess(Character letter) {
        if (letter == null) {
            throw new IllegalArgumentException("Guessed letter cannot be null.");
        }
        if (lives == 0) {
            throw new IllegalStateException("Cannot guess when player has no lives left.");
        }
        letter = Character.toLowerCase(letter);
        lives--;
        wrongGuesses.add(letter);
    }

    public Set<Character> getWrongGuesses() {
        return wrongGuesses;
    }

    public void correctGuess() {
        if (lives == 0) {
            throw new IllegalStateException("Game is over. Cannot register correct guesses.");
        }
        score++;
    }

    public Set<Character> getGuesses() {
        return guesses;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    /**
     * Checks if the player has no lives left.
     *
     * @return true if lives == 0, false otherwise
     */
    public boolean isGameOver() {
        return lives == 0;
    }

}
