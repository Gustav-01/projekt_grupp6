package se.yrgo.project;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordsTest {

    @Test
    void shouldReturnNewObject() {
        List<String> words = List.of("Java");
        Words word = new Words(words);

        assertNotNull(words);
    }

    @Test
    void shouldReturnRandomWorld() {

        List<String> words = List.of("Java", "Python", "Jenkins");
        Words word = new Words(words);
        String randomWord = word.getRandomWord();

        assertTrue(words.contains(randomWord));
    }

    @Test
    void shouldConvertWordToLetter() {
        List<String> words = List.of("Java");

        Words word = new Words(words);

        List<Character> letters = word.wordToLetters("Java");

        assertEquals('J', letters.getFirst());
    }
}
