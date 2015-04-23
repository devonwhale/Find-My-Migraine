package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 17/04/2015.
 * This class allows creation of a 'Work' object
 * The object can be used by the WorkDOA class to write details to the SQLite database.
 * It is also used by the ReviewActivity to get information from the database.
 */

public class Work {
    private long id, date;
    private int syncFlag, stress;
    private double hours;

    //Empty Constructor
    public Work(){

    }
    //Constructor
    public Work(long date, double hours, int stress){
        this.date=date;
        this.hours=hours;
        this.stress=stress;
        syncFlag = 0;
    }
    //Setters

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setStress(int stress) {
        this.stress = stress;
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

    public double getHours() {
        return hours;
    }

    public String getDisplayHours(){
        if (hours == 1.5) return "1-2";
        else if (hours == 4.0) return "3-5";
        else if (hours == 7.0) return "6-8";
        else if (hours == 9.0) return "over 8";
        else return "none";
    }

    public int getStress() {
        return stress;
    }

    @Override
    public String toString() {
        return "Work [ID: " + id + ", date: "+date+", Hours: " + hours + ", Stress: " + stress + "]" + "\n";
    }

    public String[] toStringArray(){
        String[] work = new String[5];
        work[0] = Long.toString(id);
        work[1] = Integer.toString(syncFlag);
        work[2] = Converter.getDisplayDate(date);
        work[3] = Double.toString(hours);
        work[4] = Integer.toString(stress);
        return work;
    }
}
