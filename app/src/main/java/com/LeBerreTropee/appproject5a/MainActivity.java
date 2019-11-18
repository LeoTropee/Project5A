package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;


public class MainActivity extends Activity {

    private ViewPager viewPager;
    private SlideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ViewPager viewPager = findViewById(R.id.viewPager);

        adapter = new SlideAdapter(this);
        viewPager.setAdapter(adapter);



    }




}
