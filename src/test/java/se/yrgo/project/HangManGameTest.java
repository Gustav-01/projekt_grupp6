package se.yrgo.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class HangManGameTest {

    // Test that the word is solved if all unique letters are guessed.
    @Test
    public void testIsWordSolved(){
        HangManGame game = new HangManGame();
        List <Character> word = List.of('j', 'a', 'v', 'a');
        Set<Character> guesses = Set.of('j', 'a', 'v');

        assertTrue(game.isWordSolved(word,guesses));
    }

    // Test that if isWordSolved handles an empty guess without errors.
    @Test
    void testEmptyGuesses() {
        HangManGame game = new HangManGame();
        List<Character> word = List.of('j','a','v','a');
        Set<Character> emptyGuesses = new HashSet<>();

        assertFalse(game.isWordSolved(word, emptyGuesses));
    }
}