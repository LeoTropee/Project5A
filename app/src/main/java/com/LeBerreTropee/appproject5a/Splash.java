package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Splash extends Activity {
//
//    /**
//
//     * The thread to process splash screen events
//
//     */
//
//    private Thread mSplashThread;
//
//    MediaPlayer myAudio;
//
//    /** Called when the activity is first created. */
//
//    @Override
//
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        // Splash screen view
//
//        setContentView(R.layout.splash_screen);
//
//        // Start animating the image
//
//        final ImageView splashImageView =  findViewById(R.id.loading);
//
//
//        final AnimationDrawable frameAnimation = (AnimationDrawable)splashImageView.getDrawable();
//
//        splashImageView.post(new Runnable(){
//
//            @Override
//
//            public void run() {
//
//                frameAnimation.start();
//
//            }
//
//        });
//
//        final Splash sPlashScreen = this;
//
//
//
//        // The thread to wait for splash screen events
//
//        mSplashThread =  new Thread(){
//
//            @Override
//
//            public void run(){
//
//                try {
//
//                    synchronized(this){
//
//                        // Wait given period of time or exit on touch
//
//                        wait(3000);
//
//                    }
//
//                }
//
//                catch(InterruptedException ex){
//
//                }
//
//                finish();
//
//
//
//                // Run next activity
//
//                Intent intent = new Intent();
//
//                intent.setClass(sPlashScreen, MainActivity.class);
//
//                startActivity(intent);
//
//                //stop();
//
//            }
//
//        };
//
//        mSplashThread.start();
//
//    }
//
//    @Override
//
//    public boolean onCreateOptionsMenu(Menu menu){
//
//        super.onCreateOptionsMenu(menu);
//
//        return false;
//
//    }
//
//    /**
//
//     * Processes splash screen touch events
//
//     */
//
//    @Override
//
//    public boolean onTouchEvent(MotionEvent evt)
//
//    {
//
//        if(evt.getAction() == MotionEvent.ACTION_DOWN)
//
//        {
//
//            synchronized(mSplashThread){
//
//                mSplashThread.notifyAll();
//
//            }
//
//        }
//
//        return true;
//
//    }
//
//    @Override
//
//    protected void onPause() {
//
//
//        super.onPause();
//
//        finish();
//
//    }
//
}