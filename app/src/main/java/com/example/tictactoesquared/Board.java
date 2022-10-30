package com.example.tictactoesquared;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// use singleton?

public class Board {
    private static final String TAG = "Board";

    private ArrayList<Square> squares;
    private String[] playerCharacters;
    private int turnIndex;
    private int nextSquareIndex;
    private boolean finished;
    private String gameWinner;

    // constructor for Board, creates an empty Board
    public Board(String[] chars) {
        playerCharacters = chars;
        squares = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            squares.add(new Square());
        }
        finished = false;
        nextSquareIndex = -1;
        turnIndex = 0;
        gameWinner = null;
    }

    public String getGameWinner() {
        return gameWinner;
    }

    public void makeMove(int squareIndex, int fieldIndex) throws IndexOutOfBoundsException {
        if(!finished) {
            if(nextSquareIndex == -1 || squareIndex == nextSquareIndex) {
                if(squares.get(squareIndex).fields.get(fieldIndex).move.equals("")) {
                    squares.get(squareIndex).fields.get(fieldIndex).move = playerCharacters[turnIndex % 2];
                    turnIndex++;
                    ArrayList<String> checkArrayList = new ArrayList<>();
                    for(Square.Field f : squares.get(squareIndex).fields) {
                        checkArrayList.add(f.move);
                    }
                    squares.get(squareIndex).winner = getWinner(checkArrayList);
                    if(squares.get(squareIndex).winner != null) {
                        checkArrayList.clear();
                        for(Square s : squares) {
                            // this is gonna give me null pointer exceptions
                            checkArrayList.add(s.winner);
                        }
                        gameWinner = getWinner(checkArrayList);
                    }
                    nextSquareIndex = (squares.get(squareIndex).winner == null)? fieldIndex : -1;
                }
            } else {
                throw new IndexOutOfBoundsException("NextSquareIndex: " + nextSquareIndex + " squareIndex: " + squareIndex);
            }
        } else {
            throw new IndexOutOfBoundsException("Game is finished, please start a new game");
        }
    }

    public String getWinner(ArrayList<String> arrayToCheck) {
        for (int a = 0; a < 8; a++) {
            String line;
            switch (a) {
                case 0:
                    line =  arrayToCheck.get(0) + arrayToCheck.get(1) + arrayToCheck.get(2);
                    break;
                case 1:
                    line =  arrayToCheck.get(3) + arrayToCheck.get(4) + arrayToCheck.get(5);
                    break;
                case 2:
                    line =  arrayToCheck.get(6) + arrayToCheck.get(7) + arrayToCheck.get(8);
                    break;
                case 3:
                    line =  arrayToCheck.get(0) + arrayToCheck.get(3) + arrayToCheck.get(6);
                    break;
                case 4:
                    line =  arrayToCheck.get(1) + arrayToCheck.get(4) + arrayToCheck.get(7);
                    break;
                case 5:
                    line =  arrayToCheck.get(2) + arrayToCheck.get(5) + arrayToCheck.get(8);
                    break;
                case 6:
                    line =  arrayToCheck.get(0) + arrayToCheck.get(4) + arrayToCheck.get(8);
                    break;
                case 7:
                    line =  arrayToCheck.get(2) + arrayToCheck.get(4) + arrayToCheck.get(6);
                    break;
                default:
                    line = null;
                    break;
            }

            // check for each player if winner
            for (int i = 0; i < 2; i++) {
                if (line.equals(playerCharacters[i] + playerCharacters[i] + playerCharacters[i])) {
                    return playerCharacters[i];
                }
            }
        }

        // check if draw
        for (int j = 0; j < 9; j++) {
            if(!arrayToCheck.get(j).equals("")) {
                continue;
            }
            return null;
        }
        return "draw";
    }

    public int getNextSquareIndex() {
        return nextSquareIndex;
    }

    public void printBoard() {
        for(Square s : squares) {
            Log.d(TAG, "printBoard: Square " + squares.indexOf(s) + ":");
            s.printSquare();
            Log.d(TAG, "\n");
        }
    }

    private class Square {
        private ArrayList<Field> fields;
        private String winner;

        // constructor for Square
        private Square() {
            fields = new ArrayList<>();
            for(int i = 0; i < 9; i++) {
                fields.add(new Field());
            }
            winner = null;
        }

        private Field getField(int fieldIndex) {
            return fields.get(fieldIndex);
        }

        private void setField(int index, String move) {
            fields.get(index).move = move;
        }

        private void printSquare() {
            //TODO: make this print Fields of separate lines
            for(Field f : fields) {
                Log.d(TAG, f.move + " ");
            }
        }

        // has a Field as a member variable, which is defined here
        private class Field {
            private String move;

            // constructor for Field
            private Field() {
                move = "";
            }

            private void clearField() {
                move = "";
            }
        }
    }
}
