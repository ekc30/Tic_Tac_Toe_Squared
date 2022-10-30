package com.example.tictactoesquared;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SetupFragment extends Fragment {

    private static final String TAG = "SetupFragment";

    private SetupInterface setupInterface;

    // not ideal to have these as public, would be better to somehow send a signal over from the main activity and enable them from here --> getters and setters
    private EditText txtPlayer1, txtPlayer2;
    private Button btnPlayer1, btnPlayer2;

    private boolean isFragmentEnabled;
    private boolean player1Ready, player2Ready;
    private String[] characters;

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_setup, container, false);
        txtPlayer1 = view.findViewById(R.id.edtTxtPlayer1);
        txtPlayer2 = view.findViewById(R.id.edtTxtPlayer2);
        btnPlayer1 = view.findViewById(R.id.btnPlayer1);
        btnPlayer2 = view.findViewById(R.id.btnPlayer2);
        characters = new String[2];
        isFragmentEnabled = true;

        // access member variables from the main activity
        mainActivity = (MainActivity) getActivity();


        // set the value of the interface to be the parent activity in order to call data transfer method - parent activity will implement the interface
        try {
            setupInterface = (SetupInterface) getActivity();
        } catch(ClassCastException e) {
            setupInterface = null;
            e.printStackTrace();
        }

        btnPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPlayer1.getText().toString().equals("")) {
                    if(player1Ready) {
                        characters[0] = "";
                        btnPlayer1.setText("Ready!");
                        txtPlayer1.setEnabled(true);
                        player1Ready = false;
                    } else {
                        String string = txtPlayer1.getText().toString();
                        if(string.length() == 1) {
                            if(!string.equals(characters[1])) {
                                characters[0] = string;
                                btnPlayer1.setText("Cancel");
                                txtPlayer1.setEnabled(false);
                                player1Ready = true;
                                if(player2Ready){
                                    setupInterface.getSetupData(characters);
                                }
                            } else {
                                // TODO: change this to display textView
                                Toast.makeText(getActivity(), "Character has to be different to Player 2's character!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // TODO: change this to display textView
                            Toast.makeText(getActivity(), "Please enter a valid character", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPlayer2.getText().toString().equals("")) {
                    if(player2Ready) {
                        characters[1] = "";
                        btnPlayer2.setText("Ready!");
                        txtPlayer2.setEnabled(true);
                        player2Ready = false;
                    } else {
                        String string = txtPlayer2.getText().toString();
                        if(string.length() == 1) {
                            if(!string.equals(characters[0])) {
                                characters[1] = string;
                                btnPlayer2.setText("Cancel");
                                txtPlayer2.setEnabled(false);
                                player2Ready = true;
                                if(player1Ready) {
                                    setupInterface.getSetupData(characters);
                                }
                            } else {
                                // TODO: change this to display textView
                                Toast.makeText(getActivity(), "Character has to be different to Player 1's character!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // TODO: change this to display textView
                            Toast.makeText(getActivity(), "Please enter a valid character", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return view;
    }

    public boolean isFragmentEnabled() {
        return isFragmentEnabled;
    }

    public void setFragmentEnabled(boolean fragmentEnabled) {
        isFragmentEnabled = fragmentEnabled;
        btnPlayer1.setEnabled(fragmentEnabled);
        btnPlayer2.setEnabled(fragmentEnabled);
        txtPlayer1.setEnabled(fragmentEnabled);
        txtPlayer2.setEnabled(fragmentEnabled);
        player1Ready = !fragmentEnabled;
        player2Ready = !fragmentEnabled;
        if(fragmentEnabled) {
            btnPlayer1.setText("Ready!");
            btnPlayer2.setText("Ready!");
        }
    }
}
