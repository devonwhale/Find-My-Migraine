package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 02/04/2015.
 * This class allows creation of a 'MenstrualCycle' object
 * The object can be used by the MenstrualCycleDAO class to write details to the SQLite database.
 * It is also used by the ReviewActivity to get information from the database.
 */
public class MenstrualCycle {
    private long id, date;
    private int syncFlag, yes, no, comingSoon;

    //Empty Constructor
    public MenstrualCycle(){
    }
    // Constructor
    public MenstrualCycle(long date, int yes, int no, int comingSoon){
        this.date=date;
        this.yes=yes;
        this.no=no;
        this.comingSoon=comingSoon;
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

    public void setYes(int yes) {
        this.yes = yes;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setComingSoon(int comingSoon) {
        this.comingSoon = comingSoon;
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

    public int getYes() {
        return yes;
    }

    public int getNo() {
        return no;
    }

    public int getComingSoon() {
        return comingSoon;
    }

    @Override
    public String toString() {
        return "Menstrual Cycle [ID: " + id + ", date: "+date+", Yes: " + yes + ", No: " + no +", Coming Soon: " + comingSoon+"]" + "\n";
    }

    public String menstrualStatus(){
        String status = "no details";
        if (yes == 1) {status = "Currently on menstrual cycle";}
        if (no == 1) {status = "Not currently on menstrual cycle";}
        if (comingSoon == 1) {status = "Menstrual cycle coming soon";}

        return status;
    }

    public String[] toStringArray(){
        String[] record = new String[6];
        record[0] = Long.toString(id);
        record[1] = Integer.toString(syncFlag);
        record[2] = Converter.getDisplayDate(date);
        record[3] = Integer.toString(yes);
        record[4] = Integer.toString(no);
        record[5] = Integer.toString(comingSoon);

        return record;
    }
}
