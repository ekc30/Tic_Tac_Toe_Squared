package com.example.tictactoesquared;

// game code source: https://www.geeksforgeeks.org/tic-tac-toe-game-in-java/

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingDeque;

// what's the link between the buttons and the Board?
// get the setup data across to the Board to start the game, then test and debug
public class MainActivity extends AppCompatActivity implements SetupInterface{

    private static final String TAG = "MainActivity";

    private TextView txtControl, txtNextTurn;
    private SetupFragment fragment;

    private Board board;
    private ArrayList<ButtonContainer> buttons;

    @Override
    public void getSetupData(String[] icons) {
        Toast.makeText(this, "Setup data: " + icons[0] + " " + icons[1], Toast.LENGTH_SHORT).show();
        setNewGame(icons);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtControl = findViewById(R.id.txtControl);
        txtNextTurn = findViewById(R.id.txtNextTurn);

        initButtons();
        setUpFragment();
    }

    private void setUpFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragment = new SetupFragment();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void setNewGame(String[] params) {
        board = new Board(params);
        txtControl.setText(board.getNextSquareIndex());
        // reset text of buttons to ""
    }

    private void initButtons() {
        buttons = new ArrayList<>();
        int squareIndex = 0;
        // A
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnA1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnA9), squareIndex, 8));
        }
        squareIndex++;
        // B
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnB1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnB9), squareIndex, 8));
        }
        squareIndex++;
        // C
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnC1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnC9), squareIndex, 8));
        }
        squareIndex++;
        // D
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnD1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnD9), squareIndex, 8));
        }
        squareIndex++;
        // E
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnE1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnE9), squareIndex, 8));
        }
        squareIndex++;
        // F
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnF1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnF9), squareIndex, 8));
        }
        squareIndex++;
        // G
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnG1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnG9), squareIndex, 8));
        }
        squareIndex++;
        // H
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnH1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnH9), squareIndex, 8));
        }
        squareIndex++;
        // I
        {
            buttons.add(new ButtonContainer(findViewById(R.id.btnI1), squareIndex, 0));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI2), squareIndex, 1));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI3), squareIndex, 2));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI4), squareIndex, 3));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI5), squareIndex, 4));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI6), squareIndex, 5));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI7), squareIndex, 6));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI8), squareIndex, 7));
            buttons.add(new ButtonContainer(findViewById(R.id.btnI9), squareIndex, 8));
        }
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        for(ButtonContainer b : buttons) {
            b.getButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(board != null) {
                        if(board.getGameWinner() == null) {
                            try {
                                board.makeMove(b.getSquareIndex(), b.getFieldIndex());
                                txtControl.setText(board.getNextSquareIndex());
                            } catch (IndexOutOfBoundsException e) {
                                Toast.makeText(MainActivity.this, "Exception caught: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });
        }
    }

    private void displayDialog() {

    }
}