package com.LeBerreTropee.appproject5a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
        String airportsJson = this.Read();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Log.println(Log.DEBUG,"path", "path" +MainActivity.this.getFilesDir().getAbsolutePath());
        adapter = new SlideAdapter(this);
        set = gson.fromJson(airportsJson, setType);
        adapter.setAirports(set);

        viewPager.setAdapter(adapter);
        String snowtam = ">>> ENSB (SVALBARD/LONGYEAR RWY 10/28) <<< \n" +
                "\n" +
                "SWEN0311 ENSB 10130958\n" +
                "(SNOWTAM 0311\n" +
                "A) ENSB\n" +
                "B) 10130958 C) 10\n" +
                "F) 7/7/7 G) XX/XX/XX H) 4/4/3\n" +
                "N) ALL REPORTED TWYS/2\n" +
                "R) ALL REPORTED APRONS/2\n" +
                "T) CONTAMINATION/100/100/100/PERCENT.\n" +
                ")\n";
        Parser pars = new Parser();
        Snowtam snow = pars.parse(snowtam);

        TextView title = findViewById(R.id.appTitle);

        TestPOST testPOST = new TestPOST(this);
        Log.println(Log.DEBUG,"ok",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.post created");

        testPOST.POST();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(this.getIntent().getSerializableExtra("airport") != null) {
            Log.println(Log.DEBUG,"","-----------------------------------------------------");
            Airport ap = (Airport)this.getIntent().getSerializableExtra("airport");
            adapter.getAirports().add(ap);
            adapter.notifyDataSetChanged();
            this.Write();

        }

    }


    public void Write(){
        try{
            FileOutputStream fileOut=openFileOutput("savedState.json",MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            outputWriter.write(gson.toJson(adapter.getAirports()));
            outputWriter.close();

            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String Read(){
        try{
            FileInputStream fileIn=openFileInput("savedState.json");
            InputStreamReader InputRead=new InputStreamReader(fileIn);
            char[] inputBuffer=new char[200];
            StringBuilder s= new StringBuilder();
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s.append(readstring);
            }
            InputRead.close();
            if(s.length()<5)
            {
                Log.println(Log.DEBUG,"LoadDemo","Log Demo, should only appear at first launch");
                StringBuilder sb = new StringBuilder();
                InputStream test  = getAssets().open("DemoAirports");
                InputStreamReader isr = new InputStreamReader(test);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                s = new StringBuilder(sb.toString());
            }


            Toast.makeText(getBaseContext(), s.toString(),Toast.LENGTH_SHORT).show();
            return s.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


/*
    private void saveState(Context context) {
        try {
            FileOutputStream savedState = new FileOutputStream("savedState.json");
            OutputStreamWriter osw = new OutputStreamWriter(savedState);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            osw.write(gson.toJson(adapter.getAirports()));
            osw.close();
        } catch (IOException fileNotFound) {
            Log.println(Log.WARN,"write savedState","error : " + fileNotFound.getMessage());
        }
    }

    private String read(Context context) {
        try {
            File file = new File(MainActivity.this.getFilesDir().getAbsolutePath()+"/savedState.json");
            if(!file.exists())
            {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                InputStream test  = getAssets().open("DemoAirports");
                InputStreamReader isr = new InputStreamReader(test);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                osw.write(sb.toString());
                osw.close();
                return sb.toString();


            }
                return null;

//            InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
//            Log.println(Log.DEBUG,"sb","isr value" + isr.toString());
//
//            BufferedReader bufferedReader = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String line;

//            while ((line = bufferedReader.readLine()) != null) {
//                sb.append(line);
//            }
           // Log.println(Log.DEBUG,"sb","sb is here" + sb.toString());

            //return sb.toString();
        } catch (IOException fileNotFound) {
            Log.println(Log.WARN,"read savedState","error : " + fileNotFound.getMessage());

            return null;
        }
    }

*/
    public void addAirport(View view) {
        Intent i = new Intent(MainActivity.this,Search.class);
        startActivity(i);
    }
}
