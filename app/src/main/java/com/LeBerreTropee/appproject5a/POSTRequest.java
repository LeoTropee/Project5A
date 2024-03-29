package com.LeBerreTropee.appproject5a;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


 class POSTRequest {

    private Context context;
    private RequestQueue queue;

     POSTRequest(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }



     void POST(final String IACO, final SnowtamCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://notamweb.aviation-civile.gouv.fr/Script/IHM/Bul_Aerodrome.php?Langue=FR";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        callback.onSucess(Parser.parse(parseHTML(response)));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error);
                        Log.d("",error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Calendar rightNow = Calendar.getInstance();
                String minute = Integer.toString(rightNow.get(Calendar.MINUTE));
                if (minute.length() == 1) {
                    minute = "0" + minute;
                }
                String hourOfDay = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY) - 1);
                if (hourOfDay.length() == 1) {
                    hourOfDay = "0" + hourOfDay;
                }
                String day = Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH));
                if (day.length() == 1) {
                    day = "0" + day;
                }
                String month = Integer.toString(rightNow.get(Calendar.MONTH) + 1);
                if (month.length() == 1) {
                    month = "0" + month;
                }

                String year = Integer.toString(rightNow.get(Calendar.YEAR));
                String date = year + "/" + month + "/" + day;
                String hour = hourOfDay + ":" + minute;


                Map<String, String> params = new HashMap<>();
                params.put("bResultat", "true");
                params.put("ModeAffichage", "COMPLET");
                params.put("AERO_Date_DATE", date);
                params.put("AERO_Date_HEURE", hour);
                params.put("AERO_Langue", "FR");
                params.put("AERO_Duree", "12");
                params.put("AERO_CM_REGLE", "1");
                params.put("AERO_CM_GPS", "2");
                params.put("AERO_CM_INFO_COMP", "1");
                params.put("AERO_Rayon", "10");
                params.put("AERO_Plafond", "30");

                params.put("AERO_Tab_Aero[0]", IACO);

                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }
        };

        queue.add(stringRequest);
    }

     private String parseHTML(String html) {
        int size = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'S' && html.charAt(i + 1) == 'W' && html.charAt(i + 2) == 'E' && html.charAt(i + 3) == 'N') {
                size++;
            }
        }
        StringBuilder result = new StringBuilder();
        int compteur = 0;
        int endIndex;
        int startIndex = 0;
        while (compteur < size) {
            startIndex = html.indexOf("SWEN", startIndex + 1);
            endIndex = startIndex;
            while (html.charAt(endIndex) != '<') {
                endIndex++;
            }
            compteur++;
            result.append(html.substring(startIndex, endIndex)).append("#");
        }
        return result.toString();
    }


     public RequestQueue getQueue() {
         return queue;
     }

     public void setQueue(RequestQueue queue) {
         this.queue = queue;
     }
 }
