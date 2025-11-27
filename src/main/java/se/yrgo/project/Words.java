package se.yrgo.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Words {
    private final List<String> words;
    private String randomWord;

    public Words(List<String> words) {
        this.words = words;
        this.randomWord = null;
    }

    public String getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        randomWord = words.get(randomIndex);
        return randomWord;
    }

    public List<Character> wordToLetters(String word) {
        List<Character> letters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            letters.add(c);
        }
        return letters;
    }
}
