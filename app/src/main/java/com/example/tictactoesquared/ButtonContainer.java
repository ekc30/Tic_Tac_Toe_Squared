package com.example.tictactoesquared;

import android.widget.Button;

public class ButtonContainer {
    private final Button button;
    private final int squareIndex;
    private final int fieldIndex;

    public ButtonContainer(Button button, int squareIndex, int fieldIndex) {
        this.button = button;
        this.squareIndex = squareIndex;
        this.fieldIndex = fieldIndex;
    }

    public Button getButton() {
        return button;
    }

    public int getSquareIndex() {
        return squareIndex;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }
}
