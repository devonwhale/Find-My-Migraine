package uk.ac.bradford.findmymigraine;

import java.util.Calendar;

/**
 * Created by Steve on 24/02/2015.
 * This class allows creation of a 'When' object (for recording when a migraine occurs)
 * The object can be used by the WhenDOA class to write details to the SQLite database.
 * It is also used by the ReviewActivity to get information from the database.
 */
public class When {
    private long id, date, start_time, end_time;
    private int syncFlag;

    //Empty constructor
    public When(){}

    //Constructors - id removed from constructor
    public When(long date, long start_time, long end_time){
        //this.id = id;
        this.date = date; this.start_time = start_time; this.end_time = end_time;
        //constructor, so SyncFlag will always be 0 for a new record.
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

    public String[] toStringArray(){
        String[] record = new String[5];
        record[0] = Long.toString(id);
        record[1] = Integer.toString(syncFlag);
        record[2] = Converter.getDisplayDate(date);
        record[3] = Converter.getDisplayHoursMinutes(start_time);
        record[4] = Converter.getDisplayHoursMinutes(end_time);
        return record;
    }

    public String getDisplayDate(){
        String displayDate = "";
        //code to turn long date to DD/MM/YYYY
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int displayMonth = calendar.get(Calendar.MONTH) + 1;
        displayDate = calendar.get(Calendar.DATE)+"/"+displayMonth+"/"+calendar.get(Calendar.YEAR);
        return displayDate;
    }

    public String getDisplayStartTime(){
        String displayStartTime = "";

       // /code to turn Long time to hh:mm
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(start_time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        String displayMinutes = Integer.toString(minutes);
        switch(minutes){
            case 0: displayMinutes = "00";break;
            case 1: displayMinutes = "01";break;
            case 2: displayMinutes = "02";break;
            case 3: displayMinutes = "03";break;
            case 4: displayMinutes = "04";break;
            case 5: displayMinutes = "05";break;
            case 6: displayMinutes = "06";break;
            case 7: displayMinutes = "07";break;
            case 8: displayMinutes = "08";break;
            case 9: displayMinutes = "09";break;
        }
        displayStartTime = hour+":"+displayMinutes;
        return displayStartTime;
    }

    public String getDisplayEndTime() {
        String displayEndTime = "";

        // /code to turn Long time to hh:mm
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(end_time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        String displayMinutes = Integer.toString(minutes);
        switch(minutes){
            case 0: displayMinutes = "00";break;
            case 1: displayMinutes = "01";break;
            case 2: displayMinutes = "02";break;
            case 3: displayMinutes = "03";break;
            case 4: displayMinutes = "04";break;
            case 5: displayMinutes = "05";break;
            case 6: displayMinutes = "06";break;
            case 7: displayMinutes = "07";break;
            case 8: displayMinutes = "08";break;
            case 9: displayMinutes = "09";break;
        }
        displayEndTime = hour+":"+displayMinutes;
        return displayEndTime;

    }
}
