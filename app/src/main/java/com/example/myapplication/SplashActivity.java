package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        Animation animSplash = AnimationUtils.loadAnimation(this, R.anim.splash_logo);
        ImageView splashLogo = (ImageView) findViewById(R.id.imageViewLogo);
        splashLogo.startAnimation(animSplash);

        animSplash.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intentSplashToMain = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(intentSplashToMain);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}