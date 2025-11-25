package se.yrgo.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Words {
    private final List<String> words;
    private final List<Character> wordAsLetters;
    private String randomWord;

    public Words(List<String> words) {
        this.words = words;
        this.wordAsLetters = new ArrayList<>();
        this.randomWord = null;
    }

    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        randomWord = words.get(randomIndex);
        return randomWord;
    }

    public List<Character> wordToLetters(String word) {

        for (int i = 0; i < word.length(); i++) {
            wordAsLetters.add(word.charAt(i));
        }
        return wordAsLetters;
    }
}
