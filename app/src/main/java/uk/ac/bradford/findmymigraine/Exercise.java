package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 23/02/2015.
 */
public class Exercise {
    private long id;
    private double hours;
    private int intensity;

    public Exercise() {
        // Empty Constructor
    }

    // Constructors
    public Exercise( double hours, int intensity) {
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

    public double getHours () {
        return hours;
    }

    public int getIntensity () {
        return intensity;
    }


    public String toString() {
        return "Exercise [ID: " + id + ", Hours: " + hours + ", Intensity: " + intensity + "]" + "\n";
    }
}
