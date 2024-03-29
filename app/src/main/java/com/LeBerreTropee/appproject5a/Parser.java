package com.LeBerreTropee.appproject5a;

import android.content.Context;
import android.util.Log;


import java.util.HashMap;



public class  Parser {


        static Snowtam parse(String stringSnowtam)
        {


                String oaci = "";
                String date = "";
                String runway = "";
                String clearedL= "";
                String clearedW= "";
                String state= "";
                String meanDepth= "";
                String criticalSnowbanks= "";
                String light= "";
                String clearance= "";
                String anticipedTime= "";
                String taxiways= "";
                String snowbanks= "";
                String parking= "";
                String nextObservation= "";
                String remark= "";

                String threshold = "";
                String mid = "";
                String rollOut = "";

                int id = 0;
                int end = 0;



                if(stringSnowtam.contains("A)"))
                {
                        id = stringSnowtam.indexOf("A)",id) + 2 ;
                        end = stringSnowtam.indexOf(")",id)-1;
                        oaci = stringSnowtam.substring(id,end).trim();
                }

                if(stringSnowtam.contains("B)"))
                {
                        id = stringSnowtam.indexOf("B)",end) + 2;
                        end = stringSnowtam.indexOf(")",id)-1;
                        date = stringSnowtam.substring(id,end).trim();


                        //decode B)

                        String mois = date.substring(0,2);
                        String jour = date.substring(2,4);
                        String heure = date.substring(4,6);
                        String minutes = date.substring(6,8);

                        HashMap<String, String> nomMois = new HashMap<String, String>();

                        nomMois.put("01","January");
                        nomMois.put("02","February ");
                        nomMois.put("03","March");
                        nomMois.put("04","April");
                        nomMois.put("05","May");
                        nomMois.put("06","June");
                        nomMois.put("07","July");
                        nomMois.put("08","August");
                        nomMois.put("09","September");
                        nomMois.put("10","October");
                        nomMois.put("11","November");
                        nomMois.put("12","December");


                        heure = heure  + "h" + minutes + " UTC";
                        date = jour + " " + nomMois.get(mois)  + " " + heure;


                }


                if(stringSnowtam.contains("C)")) {
                        id = stringSnowtam.indexOf("C)", end) + 2;
                        end = stringSnowtam.indexOf(")", id)-1;
                        runway = stringSnowtam.substring(id, end).trim();

                        //decode C)

                        if(runway.contains("L") || runway.contains("R")) {

                                end = runway.length()-1;
                                runway = "RUNWAY " + runway.substring(0,end);

                        }
                        else{
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
                        }

                }

                if(stringSnowtam.contains("D)")) {
                        id = stringSnowtam.indexOf("D)", end) + 2;
                        end = stringSnowtam.indexOf(")", id)-1;
                        clearedL = stringSnowtam.substring(id, end).trim();

                        //decode D)

                        clearedL = "CLEARED RUNWAY LENGTH " + clearedL + "M";

                }

                if(stringSnowtam.contains("E)")) {
                        id = stringSnowtam.indexOf("E)", end) + 2;
                        end = stringSnowtam.indexOf(")", id)-1;
                        clearedW = stringSnowtam.substring(id, end).trim();

                        //decode E)

                        if(clearedW.contains("L"))
                        {
                                clearedW = "CLEARED RUNWAY WIDTH " + clearedW.substring(0,clearedW.indexOf("L"))  + "M LEFT";
                        }
                        if(clearedW.contains("R"))
                        {
                                clearedW = "CLEARED RUNWAY WIDTH " + clearedW.substring(0,clearedW.indexOf("R"))  + "M RIGHT";
                        }
                        else{
                                clearedW = "CLEARED RUNWAY WIDTH " + clearedW.substring(0,clearedW.indexOf("R")) + "M";
                        }


                }

                if(stringSnowtam.contains("F)")) {
                        id = stringSnowtam.indexOf("F)", end) + 2;
                        end = stringSnowtam.indexOf(")", id)-1;
                        state = stringSnowtam.substring(id, end).trim();

                        //decode F)


                        end = state.indexOf("/");
                        threshold = state.substring(0,end);

                        id = end + 1;
                        end = state.substring(id).indexOf("/");
                        mid = state.substring(id,end + id);

                        rollOut = state.substring(end+id+1);

                        HashMap<String, String> conditions = new HashMap<String, String>();


                        conditions.put("0","CLEAR AND DRY");
                        conditions.put("1","DAMP");
                        conditions.put("2","WET or WATER PATCHES");
                        conditions.put("3","RIME OR FROST COVERED");
                        conditions.put("4","DRY SNOW");
                        conditions.put("5","WET SNOW");
                        conditions.put("6","SLUSH");
                        conditions.put("7","ICE");
                        conditions.put("8","COMPACTED OR ROLLED SNOW");
                        conditions.put("9","FROZEN RUTS OR RIDGES");


                        String thr = "";
                        String mi = "";
                        String roll = "";
                        int i = 0;
                        for (i = 0; i<threshold.length()-1;i++)
                        {
                                thr += " " + conditions.get(threshold.substring(i,i+1)) + " AND";
                        }
                        thr += " " + conditions.get(threshold.substring(i));
                        for (i = 0; i<mid.length()-1;i++)
                        {
                                mi += " " + conditions.get(mid.substring(i,i+1)) + " AND";
                        }
                        mi += " " + conditions.get(mid.substring(i));
                        for (i = 0; i<rollOut.length()-1;i++)
                        {
                                roll += " " + conditions.get(rollOut.substring(i,i+1)) + " AND";
                        }
                        roll += " "+ conditions.get(rollOut.substring(i));

                        if(conditions.get(threshold.substring(0,1))==null)
                        {
                                thr = "UNKNOWN";
                        }
                        if(conditions.get(mid.substring(0,1))==null)
                        {
                                mi = "UNKNOWN";
                        }
                        if(conditions.get(roll.substring(0,1))==null)
                        {
                                roll = "UNKNOWN";
                        }

                        state = "Threshold: " + thr + " / Mid runway: " + mi + " / Roll out: " +roll;

                }
                if(stringSnowtam.contains("G)")){
                        id = stringSnowtam.indexOf("G)",end) + 2;
                        end = stringSnowtam.indexOf(")",id)-1;
                        meanDepth = stringSnowtam.substring(id,end).trim();

                        //decode G)

                        end = meanDepth.indexOf("/");
                        threshold = meanDepth.substring(0,end);


                        id = end + 1;
                        end = meanDepth.substring(id).indexOf("/");

                        mid = meanDepth.substring(id,id + end);

                        rollOut = meanDepth.substring(end+1);

                        meanDepth = "Threshold: " + threshold + "mm / Mid runway: " + mid + "mm / Roll out: " + rollOut + "mm";

                        if(threshold.contains("XX")) {
                                meanDepth = "Mean depth non measurable or non significant";
                        }



                }

                if(stringSnowtam.contains("H)")) {
                        id = stringSnowtam.indexOf("H)", end) + 2;
                        end = stringSnowtam.indexOf(")", id) - 1;
                        criticalSnowbanks = stringSnowtam.substring(id, end).trim();


                        //decode H)
                }

                Snowtam snowtam = new Snowtam(stringSnowtam,oaci,date,runway,clearedL,clearedW,state,meanDepth,criticalSnowbanks,light,clearance,anticipedTime,taxiways,snowbanks,parking,nextObservation,remark);

                return (snowtam);
        }


}
