package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter adapter;
    ArrayList<Airport> set;
    POSTRequest postRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        Type setType = new TypeToken<ArrayList<Airport>>() {
        }.getType();
        String airportsJson = this.Read();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Log.println(Log.DEBUG, "path", "path" + MainActivity.this.getFilesDir().getAbsolutePath());
        postRequest = new POSTRequest(this);

        adapter = new SlideAdapter(this,this.postRequest);
        set = gson.fromJson(airportsJson, setType);
        adapter.setAirports(set);


        viewPager.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();



        if (this.getIntent().getSerializableExtra("airport") != null) {
            Log.println(Log.DEBUG, "", "-----------------------------------------------------");
            Airport ap = (Airport) this.getIntent().getSerializableExtra("airport");
            adapter.getAirports().add(ap);
            adapter.notifyDataSetChanged();
            this.Write();

        }

    }



    public void updateSnowTam() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Delete) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Write() {
        try {
            FileOutputStream fileOut = openFileOutput("savedState.json", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            outputWriter.write(gson.toJson(adapter.getAirports()));
            outputWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Read() {
        try {
            FileInputStream fileIn = openFileInput("savedState.json");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[200];
            StringBuilder s = new StringBuilder();
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s.append(readstring);
            }
            InputRead.close();
            if (s.length() < 5) {
                Log.println(Log.DEBUG, "LoadDemo", "Log Demo, should only appear at first launch");
                StringBuilder sb = new StringBuilder();
                InputStream test = getAssets().open("DemoAirports");
                InputStreamReader isr = new InputStreamReader(test);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                s = new StringBuilder(sb.toString());
            }


            return s.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void addAirport(View view) {
        Intent i = new Intent(MainActivity.this, Search.class);
        startActivity(i);
    }

    public POSTRequest getPostRequest() {
        return postRequest;
    }

    public void Delete(MenuItem item) {
        ArrayList<Airport> al =(ArrayList<Airport>) adapter.getAirports().clone();


        al.remove(adapter.getCurrentAirport());
        adapter.setAirports(al);
        adapter.notifyDataSetChanged();
        this.Write();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        Log.d("","??????????????????????????????????????????/ "+ adapter.getCount()+ "  "+ al.size());
    }
}
