package com.LeBerreTropee.appproject5a;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class Search extends AppCompatActivity {

     AutoCompleteTextView autoComplete;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchLoader loader;
        try {
            loader = new SearchLoader(this.getAssets().open("airportslist.txt"));
            Log.println(Log.DEBUG,"seach","size " + loader.getAirportNames().size());

            //On récupère le tableau de String créé dans le fichier string.xml
            String[] tableauString = new String[loader.getAirportNames().size()];
                tableauString = loader.getAirportNames().toArray(tableauString);

            //On récupère l'AutoCompleteTextView que l'on a créé dans le fichier main.xml
            autoComplete = (AutoCompleteTextView) findViewById(R.id.autocompletion);

            //On récupère le bouton que l'on a créé dans le fichier main.xml
            Button boutonRecherche = (Button) findViewById(R.id.ButtonEnvoyer);

            //On crée la liste d'autocomplétion à partir de notre tableau de string appelé tableauString
            //android.R.layout.simple_dropdown_item_1line permet de définir le style d'affichage de la liste
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, tableauString);

            //On affecte cette liste d'autocomplétion à notre objet d'autocomplétion
            autoComplete.setAdapter(adapter);

            //Enfin on rajoute un petit écouteur d'évènement sur le bouton pour afficher
            //dans un Toast ce que l'on a rentré dans notre AutoCompleteTextView
            Log.println(Log.DEBUG,"seach",loader.getAirportNames().toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void onClick() {
        Toast.makeText(this, autoComplete.getText(), Toast.LENGTH_SHORT).show();
    };
}
