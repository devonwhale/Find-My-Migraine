package uk.ac.bradford.findmymigraine;

import java.util.Calendar;

/**
 * Class to convert dates and times from milliseconds to a printable format
 * (Methods are static similar to Java's Math class.)
 * Created by Steve on 16/04/2015.
 */

public final class Converter {

    public static String getDisplayDate(Long milliseconds){
        String displayDate = "";
        //code to turn long date to DD/MM/YYYY
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int displayMonth = calendar.get(Calendar.MONTH) + 1;
        displayDate = calendar.get(Calendar.DATE)+"/"+displayMonth+"/"+calendar.get(Calendar.YEAR);
        return displayDate;
    }

    public static String getDisplayHoursMinutes(Long milliseconds){
        String displayTime ="";
        //code to turn long time to HH:MM
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
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
        String displayHours = Integer.toString(hour);
        switch(hour){
            case 0: displayHours = "00";break;
            case 1: displayHours = "01";break;
            case 2: displayHours = "02";break;
            case 3: displayHours = "03";break;
            case 4: displayHours = "04";break;
            case 5: displayHours = "05";break;
            case 6: displayHours = "06";break;
            case 7: displayHours = "07";break;
            case 8: displayHours = "08";break;
            case 9: displayHours = "09";break;
        }

        displayTime = displayHours+":"+displayMinutes;
        return displayTime;



    }

}
