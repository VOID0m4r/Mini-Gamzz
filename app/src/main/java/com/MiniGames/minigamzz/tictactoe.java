package com.MiniGames.minigamzz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
public class tictactoe extends AppCompatActivity {

    private boolean RunGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_layout);
    }
    public void BuClick(View view) {
        Button buSelected = (Button) view;
        int CellId = 0;
        if (RunGame) {
            int viewId = buSelected.getId();
            if (viewId == R.id.buClick1) {
                CellId = 1;
            } else if (viewId == R.id.buClick2) {
                CellId = 2;
            } else if (viewId == R.id.buClick3) {
                CellId = 3;
            } else if (viewId == R.id.buClick4) {
                CellId = 4;
            } else if (viewId == R.id.buClick5) {
                CellId = 5;
            } else if (viewId == R.id.buClick6) {
                CellId = 6;
            } else if (viewId == R.id.buClick7) {
                CellId = 7;
            } else if (viewId == R.id.buClick8) {
                CellId = 8;
            } else if (viewId == R.id.buClick9) {
                CellId = 9;
            }
            PlayGame(CellId, buSelected);
        }
    }


    int ActivePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList<Integer>();
    ArrayList<Integer> Player2 = new ArrayList<Integer>();

    void PlayGame(int CellID, Button buSelected) {
        Log.d("Player:", String.valueOf(CellID));
        if (CheckWin()==0) {
            if (ActivePlayer == 1 && !Player2.contains(CellID) && !Player1.contains(CellID)) {
                buSelected.setText("X");
                buSelected.setBackgroundColor(Color.GREEN);
                Player1.add(CellID);
                ActivePlayer = 2;
            }
            if (ActivePlayer == 2 && !Player1.contains(CellID) && !Player2.contains(CellID)) {
                buSelected.setText("O");
                buSelected.setBackgroundColor(Color.BLUE);
                Player2.add(CellID);
                ActivePlayer = 1;
            }
        }

        CheckWin();
    }
    public void buHome (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    int CheckWin() {
        int Winner = -1;
        //row1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)) {
            Winner = 1;
        }

        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)) {
            Winner = 2;
        }
        //row2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)) {
            Winner = 1;
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)) {
            Winner = 2;
        }
        //row3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)) {
            Winner = 2;
        }
        //colm1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)) {
            Winner = 1;
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)) {
            Winner = 2;
        }
        //colm2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)) {
            Winner = 1;
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)) {
            Winner = 2;
        }
        //colm3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)) {
            Winner = 2;
        }
        //diagonal
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)) {
            Winner = 2;
        }
        //diagonal
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)) {
            Winner = 1;
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            Winner = 2;
        }

        if (Winner != -1)
        {
            if (Winner == 2) {
                Toast.makeText(this, "\"player 2 is winner\"", Toast.LENGTH_SHORT).show();
                RunGame = false;
                return 1;
            }
            else if (Winner == 1) {
                Toast.makeText(this, "\"player 1 is winner\"", Toast.LENGTH_SHORT).show();
                RunGame = false;
                return 1;
            }
            else RunGame = false;
        }
        else if(Player1.size()==5){
            Toast.makeText(this, "\"draw\"", Toast.LENGTH_SHORT).show();
            RunGame = false;
            return 0;
        }
        return 0;
    }
}
