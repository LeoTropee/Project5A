package com.LeBerreTropee.appproject5a;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchLoader {

    private ArrayList<String> airportNames;

    private ArrayList<String> airportIACO;

    SearchLoader(InputStream is)
    {
        airportNames = new ArrayList<>();
        airportIACO = new ArrayList<>();
        try{
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        String[] splitLine;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replaceAll("\"","");
            splitLine = line.split(",");
            airportNames.add(splitLine[1]);
            airportIACO.add(splitLine[5]);
        }
    } catch (
    FileNotFoundException fileNotFound) {
            fileNotFound.getMessage();
    } catch (
    IOException ignored) {
            ignored.getMessage();
    }
    }

    ArrayList<String> getAirportNames() {
        return airportNames;
    }

    public ArrayList<String> getAirportIACO() {
        return airportIACO;
    }
}
