package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SplashScreen extends Activity {

        private ImageView container;
        private AnimationDrawable splashAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        container = findViewById(R.id.loading);
        splashAnimation = (AnimationDrawable) container.getDrawable();


    }

    @Override
    protected void onResume() {
        super.onResume();

        splashAnimation.start();
        checkAnimationStatus(50,splashAnimation);
    }

    private void checkAnimationStatus(final int time,final AnimationDrawable animationDrawable)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if( animationDrawable.getCurrent() != animationDrawable.getFrame(animationDrawable.getNumberOfFrames()-1))
                    checkAnimationStatus(time, animationDrawable);
                else
                {
                    Intent i = new Intent(SplashScreen.this,Search.class);
                    finish();
                    startActivity(i);
                }


            }
        },time);
    }
}
