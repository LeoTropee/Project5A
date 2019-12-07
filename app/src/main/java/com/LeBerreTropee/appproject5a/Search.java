package com.LeBerreTropee.appproject5a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

     AutoCompleteTextView nameSearch;
     AutoCompleteTextView IACOSearch;
    SearchLoader loader;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        try {
            loader = new SearchLoader(this.getAssets().open("airportslist.txt"));

            String[] tableauName = new String[loader.getAirportNames().size()];
            tableauName = loader.getAirportNames().toArray(tableauName);

            String[] tableauIACO = new String[loader.getAirportIACO().size()];
            tableauIACO = loader.getAirportIACO().toArray(tableauIACO);


            nameSearch = findViewById(R.id.nameSearch);
            IACOSearch =  findViewById(R.id.IACOSearch);




            //On crée la liste d'autocomplétion à partir de notre tableau de string appelé tableauString
            //android.R.layout.simple_dropdown_item_1line permet de définir le style d'affichage de la liste
            ArrayAdapter<String> NameAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, tableauName);
            ArrayAdapter<String> IACOAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line,tableauIACO);

            //On affecte cette liste d'autocomplétion à notre objet d'autocomplétion
            nameSearch.setAdapter(NameAdapter);
            nameSearch.setDropDownBackgroundResource(R.color.DropDownTextColor);

            IACOSearch.setAdapter(IACOAdapter);
            IACOSearch.setDropDownBackgroundResource(R.color.DropDownTextColor);

            //Enfin on rajoute un petit écouteur d'évènement sur le bouton pour afficher
            //dans un Toast ce que l'on a rentré dans notre AutoCompleteTextView
            Log.println(Log.DEBUG,"seach",loader.getAirportNames().toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    public void IACOSearch(View view) {

        AutoCompleteTextView IACOSearch = findViewById(R.id.IACOSearch);
        Airport ap = null;

        try {
            ap = loader.searchByIACO(IACOSearch.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(ap != null)
            {
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("airport",ap);
                finish();
                startActivity(i);
            }
            else
            {

                Toast.makeText(this,"Votre recherche n'existe pas veuillez verifier.",Toast.LENGTH_LONG).show();

            }
        }

    }

    public void nameSearch(View view) {
        AutoCompleteTextView nameSearch = findViewById(R.id.nameSearch);
        Airport ap = null;

        try {
            ap = loader.searchByName(nameSearch.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(ap != null)
            {
                Intent i = new Intent(this,MainActivity.class);
                i.putExtra("airport",ap);
                finish();
                startActivity(i);
            }
            else
            {
                Toast.makeText(this,"Votre recherche n'existe pas veuillez verifier.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
