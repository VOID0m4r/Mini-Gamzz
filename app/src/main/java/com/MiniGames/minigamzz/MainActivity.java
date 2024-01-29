package com.MiniGames.minigamzz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMM.MM/dd/h:mm");
        String dateString = sdf.format(date);
        textView.setText(dateString);
    }
    public void hangmanClick (View view){
        Intent intent = new Intent(this, hangman.class);
        startActivity(intent);
    }
    public void tictactoeClick (View view){
        Intent intent = new Intent(this, tictactoe.class);
        startActivity(intent);
    }
    public void sudokuClick (View view){
        Intent intent = new Intent(this,sudoku.class);
        startActivity(intent);
    }
}