package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 23/02/2015.
 * Date field and related methods added by Steve on 12/3/15. PARAMETER ADDED TO CONSTRUCTOR!
 * This class allows creation of a 'Exercise' object
 * The object can be used by the ExerciseDAO class to write details to the SQLite database.
 * It is also used by the ReviewActivity to get information from the database.
 */
public class Exercise {
    private long id;
    private long date;
    private double hours;
    private int intensity, syncFlag;

    public Exercise() {
        // Empty Constructor
    }

    // Constructors
    public Exercise(long date, double hours, int intensity) {
        this.date = date;
        this.hours = hours;
        this.intensity = intensity;
    }

    // Constructors
    public Exercise(double hours){
        this.hours=hours;
    }

    // Constructors
    public Exercise(int intensity){
        this.intensity=intensity;
    }


    // Setters
    public void setID (long xID) {
        this.id = xID;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setHours (double hours) {
        this.hours = hours;
    }

    public void setIntensity (int intensity) {
        this.intensity = intensity;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    // Getters
    public long getID () {
        return id;
    }

    public long getDate() {
        return date;
    }

    public double getHours () {
        return hours;
    }

    public int getIntensity () {
        return intensity;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public String toString() {
        return "Exercise [ID: " + id + ", Date: "+date+", Hours: " + hours + ", Intensity: " + intensity + "]" + "\n";
    }

    public String[] toStringArray(){
        String[] exercise = new String[5];
        exercise[0] = Long.toString(id);
        exercise[1] = Integer.toString(syncFlag);
        exercise[2] = Converter.getDisplayDate(date);
        exercise[3] = Double.toString(hours);
        exercise[4] = Integer.toString(intensity);
        return exercise;
    }
}
