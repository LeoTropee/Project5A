package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

import androidx.viewpager.widget.ViewPager;


public class MainActivity extends Activity {

    private ViewPager viewPager;
    private SlideAdapter adapter;
    ArrayList<Airport> set;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         ViewPager viewPager = findViewById(R.id.viewPager);

        Type setType = new TypeToken<ArrayList<Airport>>() {
        }.getType();
        String airportsJson = this.read(this, "airports");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String airportJson = this.read(this, "airports");
        Log.println(Log.DEBUG,"debug",airportJson);

        adapter = new SlideAdapter(this);
        set = gson.fromJson(airportJson, setType);
        adapter.airports = set;

        viewPager.setAdapter(adapter);



    }


    private String read(Context context, String fileName) {
        try {
            InputStream test  = getAssets().open("airports");
            InputStreamReader isr = new InputStreamReader(test);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            return null;
        } catch (IOException ioException) {
            return null;
        }
    }



}
