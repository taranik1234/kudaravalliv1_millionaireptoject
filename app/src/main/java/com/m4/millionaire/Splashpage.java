package com.m4.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class Splashpage extends AppCompatActivity {

    ImageView splash_image;
    Animation Animation1, Animation2;
    Handler handler;
    TextView tvWelcome;
    final int SplashTime = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        splash_image = findViewById(R.id.logo);
        Animation1 = AnimationUtils.loadAnimation(this, R.anim.fade);
        Animation1.setFillAfter(true);
        splash_image.startAnimation(Animation1);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splashpage.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        }, SplashTime);
    }
}