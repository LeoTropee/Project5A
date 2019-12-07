package com.LeBerreTropee.appproject5a;

import android.widget.Switch;

import java.lang.reflect.Array;
import java.util.HashMap;



public class  Parser {



     static Snowtam parse(String stringSnowtam)
    {

        String oaci = "";
        String date = "";
        String runway = "";

        int id = 0;

        id = stringSnowtam.indexOf("A)",id) + 2 ;
        int end = stringSnowtam.indexOf("B)",id);
        oaci = stringSnowtam.substring(id,end).trim();

        id = stringSnowtam.indexOf("B)",end) + 2;
        end = stringSnowtam.indexOf("C)",id);
        date = stringSnowtam.substring(id,end).trim();


        id = stringSnowtam.indexOf("C)",end) + 2;
        end = stringSnowtam.indexOf("\n",id);
        runway = stringSnowtam.substring(id,end).trim();





        //Decodeur


        Snowtam snowtam = new Snowtam(oaci,date,runway);


        //decode B)
        date = snowtam.getDate();
        String mois = date.substring(0,2);
        String jour = date.substring(2,4);
        String heure = date.substring(4,6);
        String minutes = date.substring(6,8);

        runway = snowtam.getRunway();

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
        date = jour + " " + nomMois.get(mois)  + " " + heure;

        snowtam.setDate(date);

        //decode C)

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

        return (snowtam);

    }

     /*static decode(Snowtam snowtam)
    {
        String date = snowtam.getDate();
        String mois = date.substring(0,2);
        String jour = date.substring(2,4);
        String heure = date.substring(4,6);
        String minutes = date.substring(6,8);

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
        date = jour + " " + nomMois.get(mois)  + " " + heure;

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







    }*/
}
