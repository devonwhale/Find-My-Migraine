package uk.ac.bradford.findmymigraine;

/**
 * Created by Imtanan on 02/03/2015.
 *
 * Needs Checking
 */
public class Coping {
    private long id, date;
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

    // Constructors
    public Coping(long id, long date, int syncFlag, int medication, int meditation, int sleep, String other) {
        this.id=id;
        this.date=date;
        this.syncFlag=syncFlag;
        this.medication=medication;
        this.meditation=meditation;
        this.sleep=sleep;
        this.other=other;
    }

    // Constructors
    public Coping(int yoga){
    this.yoga=yoga;
    }
    // Constructors
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


    //Setters
    public void setId (long xID){
        this.id=xID;
    }

    public void setDate (long xDate){
        this.date=xDate;
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
        return "Coping [ID: "+id+ ", Date: " +date+ ", Yoga: "+ yoga + ", Medication: "+medication+", Meditation: "+meditation+", Sleep: "+sleep+", Other: "+other+ "]" + "\n";
    }

}
