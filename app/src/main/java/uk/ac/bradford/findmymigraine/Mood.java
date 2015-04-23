package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 05/03/2015.
 * Date field and related methods added by Steve 12/3/15. PARAMETER ADDED TO CONSTRUCTOR!
 * This class allows creation of a 'Mood' object
 * The object can be used by the MoodDAO class to write details to the SQLite database.
 * It is also used by the ReviewActivity to get information from the database.
 */
public class Mood {

    private long id, date;
    private int mood, syncFlag;

    public Mood() {

    }

    public Mood(long date,int nMood) {
        mood = nMood; this.date=date;
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

    public String[] toStringArray() {
        String[] record = new String[4];
        record[0] = Long.toString(id);
        record[1] = Integer.toString(syncFlag);
        record[2] = Converter.getDisplayDate(date);
        record[3] = Integer.toString(mood);

        return record;
    }
}
