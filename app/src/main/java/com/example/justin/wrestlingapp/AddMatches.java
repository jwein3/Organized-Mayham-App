package com.example.justin.wrestlingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddMatches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_matches);
        SetButtons();
    }
    public void SetButtons(){

    }
    public void onBackPressed(){
        startActivity(new Intent(this, MainScreen.class));
    }
}
