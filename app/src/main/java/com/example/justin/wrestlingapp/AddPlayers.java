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
    ArrayList<String> list = MainScreen.getNamesList();
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
                EditText GetText = (EditText) findViewById(R.id.GetName);
                list.add(GetText.getText().toString());
                Toast.makeText(getApplicationContext(),"Player Added!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddPlayers.this, AddPlayers.class));


            }
        });
    }
    public void onBackPressed(){
        startActivity(new Intent(this, MainScreen.class));
    }
}
