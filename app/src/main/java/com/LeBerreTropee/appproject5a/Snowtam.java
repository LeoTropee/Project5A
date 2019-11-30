package com.LeBerreTropee.appproject5a;

public class Snowtam {

    private String oaci;
    private String date;
    private String runway;


    Snowtam(){
        oaci ="";
        date="";
        runway="";
    }

    Snowtam(String oaci, String date, String runway)
    {
        this.oaci = oaci;
        this.date = date;
        this.runway = runway;
    }


    public String getOaci() {
        return oaci;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRunway() {
        return runway;
    }

    public void setRunway(String runway) {
        this.runway = runway;
    }
}
