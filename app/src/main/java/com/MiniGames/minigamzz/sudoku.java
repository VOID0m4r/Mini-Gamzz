package com.MiniGames.minigamzz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class sudoku extends AppCompatActivity {
    GridLayout radioGridLayout;
    RadioButton radioButton;
    TextView tvChances;
    Button homeButton;
    private boolean RunGame = true;
    int Chances = 3;
    private int selectedRadioButtonId = 1;
    String setButtonNumber = "1";
    private int[][] sudokuGrid = new int[9][9];
    Button[][] buResources = new Button[9][9];
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku_layout);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String buttonID = "b" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buResources[i][j] = findViewById(resID);
                buResources[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buClick(v);
                    }
                });
            }
        }
        tvChances = findViewById(R.id.tvChances);
        radioGridLayout = findViewById(R.id.radioGridLayout);
        homeButton = findViewById(R.id.homeButton);
        setupRadioButtons();
        setRandomCells();
    }

    @SuppressLint("NonConstantResourceId")
    private void setRandomCells() {
        int gen = 0;
        while (gen < 30) {
            int randRow = rand.nextInt(9);
            int randCol = rand.nextInt(9);
            int randNum = rand.nextInt(9) + 1;

            if (isSafe(sudokuGrid, randRow, randCol, randNum)) {
                sudokuGrid[randRow][randCol] = randNum;
                buResources[randRow][randCol].setText(String.valueOf(randNum));
                buResources[randRow][randCol].setEnabled(false);
                gen++;
            }
        }
        updateUI();
    }

    public void buClick(View view) {
        Button buSelected = (Button) view;
        int viewId = buSelected.getId();
        setButtonNumber = getSelectedRadioButtonText();
        buSelected.setText(setButtonNumber);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (viewId == buResources[i][j].getId()) {
                    if (!isGridFull(sudokuGrid)) {
                        // Check if setButtonNumber is a valid integer
                        if (!setButtonNumber.isEmpty() && setButtonNumber.matches("\\d+")) {
                            int buttonNumber = Integer.parseInt(setButtonNumber);
                            if (sudokuGrid[i][j] == 0 && isSafe(sudokuGrid, i, j, buttonNumber)) {
                                changeButton(i, j);
                            } else {
                                Chances--;
                                tvChances.setText("Chances: " + Chances);
                                Toast.makeText(this, "wrong move", Toast.LENGTH_SHORT).show();
                                if (Chances == 0) {
                                    for (int x = 0; x < 9; x++) {
                                        for (int y = 0; y < 9; y++) {
                                            buResources[x][y].setEnabled(false);
                                        }
                                    }
                                    Toast.makeText(this, "you lost", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    } else {
                        RunGame = false;
                    }
                }
            }
        }
    }

    private void updateUI() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buResources[i][j].setText(String.valueOf(sudokuGrid[i][j]));
                if (sudokuGrid[i][j] != 0) {
                    buResources[i][j].setEnabled(false);
                }
            }
        }
    }

    private void setupRadioButtons() {
        int childCount = radioGridLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = radioGridLayout.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRadioButtonClicked((RadioButton) v);
                    }
                });
            }
        }
    }

    private void onRadioButtonClicked(RadioButton radioButton) {
        // Uncheck all other radio buttons
        int childCount = radioGridLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = radioGridLayout.getChildAt(i);
            if (view instanceof RadioButton && view != radioButton) {
                ((RadioButton) view).setChecked(false);
            }
        }

        // Set the selected radio button ID
        selectedRadioButtonId = radioButton.getId();

        // Show a Toast message with the selected number
        Toast.makeText(this, "Selected number is: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
    private String getSelectedRadioButtonText() {
        if (selectedRadioButtonId != 1) {
            radioButton = findViewById(selectedRadioButtonId);
            return radioButton.getText().toString();
        }
        return "1";
    }
    public void buHome (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void changeButton(int row, int col) {
        sudokuGrid[row][col] = Integer.parseInt(setButtonNumber);
        updateUI();
    }
    private boolean isSafe(int[][] grid, int row, int col, int num) {
        // Row check
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num) {
                return false;
            }
        }

        // Column check
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == num) {
                return false;
            }
        }

        // Box check
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }
    private boolean isGridFull(int[][] grid) {
        for (int[] row : grid) {
            for (int num : row) {
                if (num == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}