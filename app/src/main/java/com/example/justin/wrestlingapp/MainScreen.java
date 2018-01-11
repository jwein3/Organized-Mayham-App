package com.example.justin.wrestlingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        SetButtons();
       

        }
    public void SetButtons(){
        Button ManagePlayersButton = (Button) (findViewById(R.id.btnManagePlayers));
        Button ManageMatchesButton = (Button) (findViewById(R.id.btnManageMatches));
        ManagePlayersButton.setOnClickListener((View.OnClickListener) this);
        ManageMatchesButton.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick (View v){
        if (v.getId() == R.id.btnManagePlayers) {
            startActivity(new Intent(MainScreen.this, ManagePlayers.class));
        }
        else if(v.getId() == R.id.btnManageMatches){
            startActivity(new Intent(MainScreen.this, ManageMatches.class));
        }
    }
}


