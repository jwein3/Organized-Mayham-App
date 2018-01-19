package com.example.justin.wrestlingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPlayers extends AppCompatActivity {
    ArrayList list = MainScreen.getNamesList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        SetButtons();

    }

    public void SetButtons() {
        Button AddPlayersButton = (Button) (findViewById(R.id.btnAddPlayers));
        AddPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EditText GetText = (EditText) findViewById(R.id.GetName);
                    EditText GetMatchNum = (EditText) findViewById(R.id.GetMatchNumber);
                    //list.add(GetText.getText().toString();
                    list.add(new MainScreen.WrestlerList(GetText.getText().toString(), Integer.parseInt(GetMatchNum.getText().toString())));
                    Toast.makeText(getApplicationContext(), "Player Added!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddPlayers.this, AddPlayers.class));
                } catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please Enter Numbers Only!",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(this, MainScreen.class));
    }
}
