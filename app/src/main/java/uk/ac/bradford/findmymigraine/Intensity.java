package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 16/04/2015.
 */
public class Intensity {
    private long id;
    private String intensity;
    private long date;
    private int syncFlag;

    public Intensity() {

    }

    /**
     *
     * @param nIntensity The String associated with the intensity. It should be a string of 16 binary values.
     */
    public Intensity(String nIntensity) {
        intensity = nIntensity;
    }

    public Intensity(String nIntensity, long nDate) {
        intensity = nIntensity;
        date = nDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    public String toString() {
        return "Intensity [ID: " + id + ", Intensity: " + intensity + "]" + "\n";
    }










    public String[] toStringArray() {
        String[] record = new String[4];
        record[0] = Long.toString(id);
        record[1] = Integer.toString(syncFlag);
        record[2] = Converter.getDisplayDate(date);
        record[3] = intensity;

        return record;
    }


}

