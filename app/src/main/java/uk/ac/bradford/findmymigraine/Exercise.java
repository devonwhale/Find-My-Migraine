package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 23/02/2015.
 * Date field and related methods added by Steve on 12/3/15.
 */
public class Exercise {
    private long id;
    private long date;
    private double hours;
    private int intensity;

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


    public String toString() {
        return "Exercise [ID: " + id + ", Date: "+date+", Hours: " + hours + ", Intensity: " + intensity + "]" + "\n";
    }
}
