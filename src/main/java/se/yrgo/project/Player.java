package se.yrgo.project;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    private int lives;
    private Set<Character> guesses;
    private int score;

    public Player(String name) {
        this.name = name;
        this.lives = 10;
        this.guesses = new HashSet<>();
        this.score = 0;
    }
    //The guess method guesses a letter, checking if the letter has been guessed before.
    // If the letter has been guessed before, @return false, if the letter has not appeared yet,
    // add it to the Hashset.
    public boolean guess(Character letter) {
        letter = Character.toLowerCase(letter);
        if (guesses.contains(letter))
        {return false;}
        else {
            guesses.add(letter);
            return true;
        }
    }
    public int totalGuesses() {
        return guesses.size();
    }
    public void wrongGuess() {
        lives--;
    }
    public void correctGuess() {
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
    public boolean isGameOver() {
        return lives == 0;
    }
}