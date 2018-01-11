package com.example.justin.wrestlingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ArrayList <String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(list, this);
        ListView MatchList = (ListView)findViewById(R.id.MatchList);
        MatchList.setAdapter(adapter);
        SetButtons();
       

        }
    public void SetButtons(){
        Button ManagePlayersButton = (Button) (findViewById(R.id.btnAddPlayers));
        Button ManageMatchesButton = (Button) (findViewById(R.id.btnAddMatches));
        ManagePlayersButton.setOnClickListener((View.OnClickListener) this);
        ManageMatchesButton.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick (View v){
        if (v.getId() == R.id.btnAddPlayers) {
            startActivity(new Intent(MainScreen.this, ManagePlayers.class));
        }
        else if(v.getId() == R.id.btnAddMatches){
            startActivity(new Intent(MainScreen.this, ManageMatches.class));
        }
    }
}


