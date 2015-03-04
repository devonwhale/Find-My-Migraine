package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 28/01/2015.
 */
public class Sleep {
    private long id, time_to_bed, time_up;
    private long wakeDate;       //date added by Steve (and to main constructor + setter, getter and toString
    private int sleep_rating, syncFlag;
    public Sleep() {
        // Empty Constructor
    }

    // Constructors
    public Sleep(long time_to_bed, long time_up) {
        this.time_to_bed = time_to_bed;
        this.time_up = time_up;
    }

    // Constructors
    public Sleep(
            long date,
            long time_to_bed, long time_up, int sleep_rating) {
        wakeDate = date;
        this.time_to_bed = time_to_bed;
        this.time_up = time_up;
        this.sleep_rating=sleep_rating;
        syncFlag = 0;
    }

    // Constructors
    public Sleep(int sleep_rating){
        this.sleep_rating=sleep_rating;
    }


    // Setters
    public void setID (long xID) {
        this.id = xID;
    }

    public void setTimeUp (long time_up) {
        this.time_up = time_up;
    }

    public void setTimeToBed (long time_to_bed) {
        this.time_to_bed = time_to_bed;
    }

    public void setSleepRating (int sleep_rating) {
        this.sleep_rating = sleep_rating;
    }

    public void setDate (long date) {wakeDate = date; }

    // Getters
    public long getID () {
        return id;
    }

    public long getDate() {
        return wakeDate;
    }

    public long getTimeUp () {
        return time_up;
    }

    public long getTimeToBed () {
        return time_to_bed;
    }

    public int getSleepRating () {
        return sleep_rating;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public String toString() {
        return "Sleep [ID: " + id +
                ", Date: "+wakeDate+
                ", Time to bed: " + time_to_bed + ", Time Up: " + time_up + ", Sleep Rating " + sleep_rating  + "]" + "\n";
    }
}
