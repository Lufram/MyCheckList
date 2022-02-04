package com.example.mychecklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mychecklist.db.DbController;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Animation animSplash = AnimationUtils.loadAnimation(this, R.anim.splash_logo);
        Animation animLog1 = AnimationUtils.loadAnimation(this, R.anim.loading);
        Animation animLog2 = AnimationUtils.loadAnimation(this, R.anim.loading2);
        Animation animLog3 = AnimationUtils.loadAnimation(this, R.anim.loading3);
        ImageView splashLogo = findViewById(R.id.imageViewLogo);
        TextView l1 = findViewById(R.id.textViewPoint1);
        TextView l2 = findViewById(R.id.textViewPoint2);
        TextView l3 = findViewById(R.id.textViewPoint3);
        splashLogo.startAnimation(animSplash);
        l3.startAnimation(animLog3);
        l2.startAnimation(animLog2);
        l1.startAnimation(animLog1);


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