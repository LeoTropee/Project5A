package com.LeBerreTropee.appproject5a;

import android.widget.Switch;

import java.lang.reflect.Array;
import java.util.HashMap;

public class Parser {

    public Parser() {

    }

     Snowtam parse(String stringSnowtam)
    {

        String oaci = "";
        String date = "";
        String runway = "";

        int id = 0;

        id = stringSnowtam.indexOf("A)",id);
        int end = stringSnowtam.indexOf("B)",id);
        oaci = stringSnowtam.substring(id,end).trim();

        id = stringSnowtam.indexOf("B)",end);
        end = stringSnowtam.indexOf("C)",id);
        date = stringSnowtam.substring(id,end).trim();


        id = stringSnowtam.indexOf("C)",end);
        end = stringSnowtam.indexOf("\n",id);
        runway = stringSnowtam.substring(id,end).trim();



        Snowtam snowtam = new Snowtam(oaci,date,runway);

        return (snowtam);
    }

     void decode(Snowtam snowtam)
    {
        String date = snowtam.getDate();
        String mois = date.substring(0,1);
        String jour = date.substring(2,3);
        String heure = date.substring(4,5);
        String minutes = date.substring(6,7);

        String runway = snowtam.getRunway();

        HashMap<String, String> nomMois = new HashMap<String, String>();
        nomMois.put("01","JANVIER");
        nomMois.put("02","FEVRIER ");
        nomMois.put("03","MARS");
        nomMois.put("04","AVRIL");
        nomMois.put("05","MAI");
        nomMois.put("06","JUIN");
        nomMois.put("07","JUILLET");
        nomMois.put("08","AOUT");
        nomMois.put("09","SEPTEMBRE");
        nomMois.put("10","OCTOBRE");
        nomMois.put("11","NOVEMBRE");
        nomMois.put("12","DECEMBRE");


        heure = heure  + "h" + minutes + " UTC";
        date = jour + " " + nomMois.get(mois) + " ";

        snowtam.setDate(date);


        int numWay = Integer.parseInt(runway);
        if (numWay > 50)
        {
            numWay = numWay % 50;
            runway = "RUNWAY " + Integer.toString(numWay);
        }
        if (numWay == 88){
            runway = "ALL RUNWAYS";
        }
        else{
            runway = "RUNWAY " + Integer.toString(numWay);
        }



        snowtam.setRunway(runway);







    }
}
