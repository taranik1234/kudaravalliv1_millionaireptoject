package com.m4.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameLost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        getSupportActionBar().hide();
    }
    public void Home(View view) {
        Intent i =  new Intent(this, HomePage.class);
        startActivity(i);
    }
}
