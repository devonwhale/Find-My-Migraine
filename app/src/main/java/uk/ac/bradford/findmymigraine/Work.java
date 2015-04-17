package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 17/04/2015.
 */
public class Work {
    private long id, date;
    private int syncFlag, hours, stress;

    //Empty Constructor
    public Work(){

    }
    //Constructor
    public Work(long date, int hours, int stress){
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

    public void setHours(int hours) {
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

    public int getHours() {
        return hours;
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
        work[3] = Integer.toString(hours);
        work[4] = Integer.toString(stress);
        return work;
    }
}
