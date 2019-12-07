package com.LeBerreTropee.appproject5a;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

 class SearchLoader {

    private ArrayList<String> airportNames;

    private ArrayList<String> airportIACO;

    private InputStream is;

    SearchLoader(InputStream iS) {
        airportNames = new ArrayList<>();
        airportIACO = new ArrayList<>();
        is = iS;
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            String[] splitLine;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                splitLine = line.split(",");
                airportNames.add(splitLine[1]);
                airportIACO.add(splitLine[5]);
            }
        } catch (
                IOException fileNotFound) {
            fileNotFound.getMessage();
        }
    }

    Airport searchByIACO(String IACO) throws IOException {
        is.reset();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String s;

        while ((s = bufferedReader.readLine()) != null) {

            if (s.contains(IACO)) {

                String[] sSplited = s.split(",");
                Log.println(Log.DEBUG, "osef", sSplited[1]);
                return new Airport(sSplited[1].replace("\"", ""),
                        sSplited[5].replace("\"", ""), Double.parseDouble(sSplited[6]),
                        Double.parseDouble(sSplited[7]));
            }

        }

        return null;
    }

    Airport searchByName(String name) throws IOException {
        is.reset();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String s;

        while ((s = bufferedReader.readLine()) != null) {

            if (s.contains(name)) {

                String[] sSplited = s.split(",");
                Log.println(Log.DEBUG, "osef", sSplited[1]);
                return new Airport(sSplited[1].replace("\"", ""),
                        sSplited[5].replace("\"", ""), Double.parseDouble(sSplited[6]),
                        Double.parseDouble(sSplited[7]));
            }
        }
        return null;

    }

    ArrayList<String> getAirportNames() {
        return airportNames;
    }

     ArrayList<String> getAirportIACO() {
        return airportIACO;
    }
}
