package com.MiniGames.minigamzz;

import android.content.Intent;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class hangman extends AppCompatActivity {

    private String[] words = {"elephant", "bear", "turtle", "horse", "dog", "lion"};
    private String chosenWord;
    private StringBuilder guessedWord;
    private int maxAttempts = 6;
    private int remainingAttempts;
    private TextView wordTextView;
    private EditText guessEditText;
    private Button guessButton;
    private TextView attemptsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangmanlayout);

        wordTextView = findViewById(R.id.wordTextView);
        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);
        attemptsTextView = findViewById(R.id.attemptsTextView);

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guess();
            }
        });

        startGame();
    }

    private void startGame() {
        chosenWord = chooseRandomWord();
        guessedWord = new StringBuilder();
        for (int i = 0; i < chosenWord.length(); i++) {
            guessedWord.append("_");
        }
        remainingAttempts = maxAttempts;

        updateUI();
    }

    private void updateUI() {
        wordTextView.setText("Word: " + guessedWord.toString());
        attemptsTextView.setText("Attempts left: " + remainingAttempts);
    }
    public void buHome (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void Guess() {
        String guessInput = guessEditText.getText().toString().toLowerCase();

        if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
            char guess = guessInput.charAt(0);
            if (!updateGuessedWord(guess)) {
                remainingAttempts--;
            }

            if (remainingAttempts > 0 && guessedWord.toString().contains("_")) {
                updateUI();
            } else {
                endGame();
            }
        } else {
            // Invalid input
            guessEditText.setError("Please enter a single letter.");
        }
    }

    private String chooseRandomWord() {
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    private boolean updateGuessedWord(char guess) {
        boolean correctGuess = false;

        for (int i = 0; i < chosenWord.length(); i++) {
            if (chosenWord.charAt(i) == guess && guessedWord.charAt(i) == '_') {
                guessedWord.setCharAt(i, guess);
                correctGuess = true;
            }
        }

        return correctGuess;
    }

    private void endGame() {
        if (remainingAttempts > 0) {
            wordTextView.setText("Congratulations! You guessed the word: " + chosenWord);
        } else {
            wordTextView.setText("Sorry, you ran out of attempts. The correct word was: " + chosenWord);
        }

        guessEditText.setEnabled(false);
        guessButton.setEnabled(false);
    }
}