package uk.ac.bradford.findmymigraine;

/**
 * Created by Steve on 24/02/2015.
 * This class allows creation of a 'When' object (for recording when a migraine occurs)
 * The object can be used by the WhenDOA class to write details to the SQLite database.
 */
public class When {
    private long id, date, start_time, end_time;
    private int syncFlag;

    //Empty constructor
    public When(){}

    //Constructors - should we have id here??????
    public When(long id, long date, long start_time, long end_time){
        this.id = id; this.date = date; this.start_time = start_time; this.end_time = end_time;
        syncFlag = 0;
    }

    //Setters

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    //Getters

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public long getStart_time() {
        return start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    @Override
    public String toString() {
        return "When [ID: " + id + ", Date: " + date + ", Start time: " + start_time + ", End time " + end_time  + "]" + "\n";
    }
}
