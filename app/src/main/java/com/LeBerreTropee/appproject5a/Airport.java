package com.LeBerreTropee.appproject5a;

import java.io.Serializable;

public class Airport implements Serializable {

    private final String name;
    private final String IACO;
    private final double latitude;
    private final double longitude;

    private transient Snowtam snowtam;



    public Airport(String name, String IACO, double latitude, double longitude) {
        this.name = name;
        this.IACO = IACO;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getIACO() {
        return IACO;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Snowtam getSnowtam() {
        return snowtam;
    }

    public void setSnowtam(Snowtam snowtam) {
        this.snowtam = snowtam;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", IACO='" + IACO + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
