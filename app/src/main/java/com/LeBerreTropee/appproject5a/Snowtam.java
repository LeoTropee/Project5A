package com.LeBerreTropee.appproject5a;

public class Snowtam {

    private String coded;
    private String oaci;
    private String date;
    private String runway;
    private String clearedL;
    private String clearedW;
    private String state;
    private String meanDepth;
    private String criticalSnowbanks;
    private String light;
    private String clearance;
    private String anticipedTime;
    private String taxiways;
    private String snowbanks;
    private String parking;
    private String nextObservation;
    private String remark;

    Snowtam(){
        oaci ="";
        date="";
        runway="";
        clearedL="";
        clearedW="";
        state="";
        meanDepth="";
        criticalSnowbanks="";
        light="";
        clearance="";
        anticipedTime="";
        taxiways="";
        snowbanks="";
        parking="";
        nextObservation="";
        remark="";
    }

    public Snowtam(String coded,String oaci, String date, String runway, String clearedL, String clearedW, String state, String meanDepth, String criticalSnowbanks, String light, String clearance, String anticipedTime, String taxiways, String snowbanks, String parking, String nextObservation, String remark) {
        this.coded =
        this.oaci = oaci;
        this.date = date;
        this.runway = runway;
        this.clearedL = clearedL;
        this.clearedW = clearedW;
        this.state = state;
        this.meanDepth = meanDepth;
        this.criticalSnowbanks = criticalSnowbanks;
        this.light = light;
        this.clearance = clearance;
        this.anticipedTime = anticipedTime;
        this.taxiways = taxiways;
        this.snowbanks = snowbanks;
        this.parking = parking;
        this.nextObservation = nextObservation;
        this.remark = remark;
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

    public String getClearedL() {
        return clearedL;
    }

    public void setClearedL(String cleared) {
        this.clearedL = cleared;
    }

    public String getClearedW() {
        return clearedW;
    }

    public void setClearedW(String clearedW) {
        this.clearedW = clearedW;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMeanDepth() {
        return meanDepth;
    }

    public void setMeanDepth(String meanDepth) {
        this.meanDepth = meanDepth;
    }

    public String getCriticalSnowbanks() {
        return criticalSnowbanks;
    }

    public void setCriticalSnowbanks(String criticalSnowbanks) {
        this.criticalSnowbanks = criticalSnowbanks;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }

    public String getAnticipedTime() {
        return anticipedTime;
    }

    public void setAnticipedTime(String anticipedTime) {
        this.anticipedTime = anticipedTime;
    }

    public String getTaxiways() {
        return taxiways;
    }

    public void setTaxiways(String taxiways) {
        this.taxiways = taxiways;
    }

    public String getSnowbanks() {
        return snowbanks;
    }

    public void setSnowbanks(String snowbanks) {
        this.snowbanks = snowbanks;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getNextObservation() {
        return nextObservation;
    }

    public void setNextObservation(String nextObservation) {
        this.nextObservation = nextObservation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Snowtam{" +
                "oaci='" + oaci + '\'' +
                ", date='" + date + '\'' +
                ", runway='" + runway + '\'' +
                ", clearedL='" + clearedL + '\'' +
                ", clearedW='" + clearedW + '\'' +
                ", state='" + state + '\'' +
                ", meanDepth='" + meanDepth + '\'' +
                ", criticalSnowbanks='" + criticalSnowbanks + '\'' +
                ", light='" + light + '\'' +
                ", clearance='" + clearance + '\'' +
                ", anticipedTime='" + anticipedTime + '\'' +
                ", taxiways='" + taxiways + '\'' +
                ", snowbanks='" + snowbanks + '\'' +
                ", parking='" + parking + '\'' +
                ", nextObservation='" + nextObservation + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
