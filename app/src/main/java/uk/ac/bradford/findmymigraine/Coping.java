package uk.ac.bradford.findmymigraine;

import java.security.PublicKey;

/**
 * Created by Imtanan on 02/03/2015.
 *
 * Needs Checking
 *
 * This class allows creation of a 'Coping' object
 * The object can be used by the CopingDAO class to write details to the SQLite database.
 * It is also used by the ReviewAttacks to get information from the database.
 */
public class Coping {
    private long id, date, startTime;
    private int syncFlag, yoga, medication, meditation, sleep;
    private String other;

    public Coping() {
        // Empty Constructor
    }

    // Constructors
    public Coping(long id, long date) {
        this.id = id;
        this.date = date;
        syncFlag=0;
    }

    public Coping(long date, long startTime, int medication, int meditation, int sleep, int yoga){
        this.date=date;
        this.startTime = startTime;
        this.medication=medication;
        this.meditation=meditation;
        this.sleep=sleep;
        this.yoga=yoga;
        syncFlag=0;
    }

    // Constructors
    public Coping(long date,long startTime,  int medication, int meditation, int sleep, int yoga, String other) {   //amended 13/4/15 by Steve to put yoga to sixth parameter instead of third.
       // this.id=id;
        this.date=date;
        this.startTime = startTime;
       // this.syncFlag=syncFlag;
        this.yoga = yoga;
        this.medication=medication;
        this.meditation=meditation;
        this.sleep=sleep;
        this.other=other;
        syncFlag=0;
    }

    // Constructors
    public Coping(int yoga){
    this.yoga=yoga;
    }
    // Constructors
    /*
    public Coping(int medication){

        this.medication=medication;
    }
    // Constructors
    public Coping(int meditation){
        this.meditation=meditation;
    }
    // Constructors
    public Coping(int sleep){
        this.sleep=sleep;
    }

    public Coping(String other){
        this.other=other;
    }
    */


    //Setters
    public void setId (long xID){
        this.id=xID;
    }

    public void setDate (long xDate){
        this.date=xDate;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setSyncFlag(int xsyncFlag) {
        this.syncFlag = xsyncFlag;
    }

    public void setYoga(int xYoga) {
        this.yoga= xYoga;
    }

    public void setMedication(int xMedication) {
        this.medication = xMedication;
    }

    public void setMeditation(int xMeditation) {
        this.meditation = xMeditation;
    }

    public void setSleep(int xSleep) {
        this.sleep = xSleep;
    }

    public void setOther(String xOther) {
        this.other = xOther;
    }

    //Getters

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public int getYoga() {
        return yoga;
    }

    public int getMedication() {
        return medication;
    }

    public int getMeditation() {
        return meditation;
    }

    public int getSleep() {
        return sleep;
    }

    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Coping [ID: "+id+ ", Date: " +date+", Start Time: "+startTime+ ", Yoga: "+ yoga + ", Medication: "+medication+", Meditation: "+meditation+", Sleep: "+sleep+", Other: "+other+ "]" + "\n";
    }

    public String copingList(){
        String coping = "";
        if (yoga == 1) coping += " * Yoga";
        if (medication == 1) coping += " * Medication";
        if (meditation == 1) coping += " * Meditation";
        if (sleep == 1) coping += " * Sleep";
        if (other != "") coping += " * "+other;
        return coping;
    }

    public String[] toStringArray(){
        String[] record = new String[8];
        record[0] = Long.toString(id);
        record[1] = Integer.toString(syncFlag);
        record[2] = Converter.getDisplayDate(date);
        record[3] = Integer.toString(yoga);
        record[4] = Integer.toString(medication);
        record[5] = Integer.toString(meditation);
        record[6] = Integer.toString(sleep);
        record[7] = other;

        return record;
    }
}
