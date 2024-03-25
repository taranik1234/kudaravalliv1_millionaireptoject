package com.m4.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WinningPage extends AppCompatActivity {
    TextView Amount, tvCongrats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        getSupportActionBar().hide();
        Amount = (TextView) findViewById(R.id.amount);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String message = bundle.getString("prize_amt");
            message = "You won $" + message;
            Amount.setText(message);
        }
        else
        {
            startActivity(new Intent(this, HomePage.class));
        }
    }
}