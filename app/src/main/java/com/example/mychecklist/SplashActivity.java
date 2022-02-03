package com.example.mychecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mychecklist.db.DbController;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{

    private DbController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        dbController = new DbController(this);
        dbController.sessionId = 0;
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