package fi.tuni.prog3.wordgame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordGame {
    private List<String> words;
    private WordGameState gameState;
    private boolean gameActive;

    public WordGame(String wordFilename)  {
        words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(wordFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameActive = false;
    }

    public void initGame(int wordIndex, int mistakeLimit) throws GameStateException{
        if (wordIndex < 0 || wordIndex >= words.size()) {
            throw new IllegalArgumentException("Invalid word index");
        }
        String selectedWord = words.get(wordIndex);
        gameState = new WordGameState(selectedWord, mistakeLimit);
        gameActive = true;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public WordGameState getGameState() throws GameStateException{
        if (!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        return gameState;
    }

    public WordGameState guess(char c) throws GameStateException{
        if (!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        String word = gameState.getWord().toLowerCase();
        char[] wordChars = word.toCharArray();
        boolean correctGuess = false;
        for (int i = 0; i < wordChars.length; i++) {
            if (Character.toLowerCase(wordChars[i]) == Character.toLowerCase(c)) {
                correctGuess = true;
                if (wordChars[i] != c) {
                    wordChars[i] = c;
                    gameState.missingChars--;
                }
            }
        }
        if (!correctGuess) {
            gameState.mistakes++;
        }
        if (gameState.missingChars == 0 || gameState.mistakes >= gameState.mistakeLimit) {
            gameActive = false;
        }
        gameState.word = new String(wordChars);
        return gameState;
    }

    public WordGameState guess(String word) throws GameStateException {
        if (!isGameActive()) {
            throw new GameStateException("There is currently no active word game!");
        }
        if (word.equalsIgnoreCase(gameState.word)) {
            gameState.missingChars = 0;
            gameActive = false;
        } else {
            gameState.mistakes++;
            if (gameState.mistakes >= gameState.mistakeLimit) {
                gameActive = false;
            }
        }
        return gameState;
    }

    public class WordGameState {
        private String word;
        private int mistakes;
        private int mistakeLimit;
        private int missingChars;

        

        public String getWord() {
            return word;
        }

        public int getMistakes() {
            return mistakes;
        }

        public int getMistakeLimit() {
            return mistakeLimit;
        }

        public int getMissingChars() {
            return missingChars;
        }
    }
}
