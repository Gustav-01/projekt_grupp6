package se.yrgo.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    // Constructor Exception Tests

    @Test
    public void testConstructorException_NameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Player(null, 10));
    }

    @Test
    public void testConstructorException_NameEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Player(" ", 10));
    }

    @Test
    public void testConstructorException_LivesAreZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Player("H ", -1));
        assertThrows(IllegalArgumentException.class, () -> new Player("H", 0));
    }

    // Guess() Test
    @Test
    public void guess_ThrowsException_LetterNull() {
        Player player = new Player("H ", 10);
        assertThrows(IllegalArgumentException.class, () -> player.guess(null));
    }

    @Test
    public void guess_ThrowsException_LetterIsNotAlphabet() {
        Player player = new Player("H ", 10);

        assertThrows(IllegalArgumentException.class, () -> player.guess('1'));
        assertThrows(IllegalArgumentException.class, () -> player.guess('%'));
        assertThrows(IllegalArgumentException.class, () -> player.guess('ö'));
    }

    @Test
    public void guess_ReturnTrueWithNewLetter() {
        Player player = new Player("H ", 10);
        boolean guess = player.guess('a');
        assertTrue(guess, "New letter should return true");
        assertTrue(player.getGuesses().contains('a'), "Guesses should contain the letter 'a' ");
    }

    @Test
    public void guess_ReturnFalseWithLetterAlreadyGuessed() {
        Player player = new Player("H ", 10);
        player.guess('b');
        boolean guess = player.guess('b');
        assertFalse(guess, "Letter already guessed should return false");
        assertEquals(1, player.getGuesses().size(), "Guesses should not duplicate letters");


    }

    //wrongGuess() Test
    @Test
    public void wrongGuess_ThrowsException_LetterIsNull() {
        Player player = new Player("H ", 10);
        assertThrows(IllegalArgumentException.class, () -> player.wrongGuess(null));
    }

    @Test
    public void wrongGuess_ThrowsException_LivesIsZero() {
        Player player = new Player("H ", 1);
        player.wrongGuess('b');
        assertEquals(0, player.getLives());
        assertThrows(IllegalStateException.class, () -> player.wrongGuess('b'));
    }
    @Test
    public void wrongGuess_DecreaseLives_AddToWrongGuesses() {
        Player player = new Player("H ", 1);
        int lives = player.getLives();
        player.wrongGuess('a');
        assertEquals(lives - 1, player.getLives(), "Lives should decrease by 1");
        assertTrue(player.getWrongGuesses().contains('a'), "Wrong guesses should contain the letter 'a' ");
    }


    // correctGuess() Test

    @Test
    public void guess_ThrowsException_LivesIsZero() {
        Player player = new Player("H ", 1);
        player.wrongGuess('b');
        assertEquals(0, player.getLives());
        assertThrows(IllegalStateException.class, () -> player.correctGuess());
    }

    // isGameOver() Test

    @Test
    public void isGameOver_ReturnsFalse() {
        Player player = new Player("H ", 5);
        assertFalse(player.isGameOver(), "Game should not be over when lives > 0");
    }

    @Test
    public void isGameOver_ReturnsTrue() {
        Player player = new Player("H ", 1);
        player.wrongGuess('b');
        assertTrue(player.isGameOver(), "Game should be over when lives = 0");
    }
}