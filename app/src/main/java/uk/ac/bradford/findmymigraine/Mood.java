package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 05/03/2015.
 * Date field and related methods added by Steve 12/3/15. PARAMETER ADDED TO CONSTRUCTOR!
 */
public class Mood {

    private long id, date;
    private int mood;

    public Mood() {

    }

    public Mood(long date,int nMood) {
        mood = nMood;
    }

    public long getId() {
        return id;
    }

    public int getMood() {
        return mood;
    }

    public void setId(long nId) {
        id = nId;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setMood(int nMood) {
        mood = nMood;
    }

    public long getDate() {
        return date;
    }

    public String toString() {
        return "Mood [ID: " + id + ", date: "+date+", Mood: " + mood + "]" + "\n";
    }

}
