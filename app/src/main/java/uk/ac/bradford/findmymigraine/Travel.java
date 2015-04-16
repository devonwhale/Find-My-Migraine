package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 05/03/2015.
 * Date field added by Steve on 12/3/15. PARAMETER ADDED TO CONSTRUCTOR!
 */
public class Travel {

    private long id, syncFlag, date;
    private double hours;
    private String method, dest;

    public Travel() {

    }

    public Travel(long date, double nHours, String nMethod, String nDest) {
        this.date = date;
        hours = nHours;
        method = nMethod;
        dest = nDest;
    }

    public void setId(long nId) {
        id = nId;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setHours(double nHours) {
        hours = nHours;
    }

    public void setMethod(String nMethod) {
        method = nMethod;
    }

    public void setDest(String nDest) {
        dest = nDest;
    }

    public void setSyncFlag(long syncFlag) {
        this.syncFlag = syncFlag;
    }

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }

    public String getMethod() {
        return method;
    }

    public String getDest() {
        return dest;
    }

    public long getSyncFlag(){return syncFlag;}

    public String toString() {
        return "Travel [ID: " + id + ", date: "+date+", Hours: " + hours + ", Method: " + method + ", Destination:" + dest + "]" + "\n";
    }

    public String[] toStringArray(){
        String[] travel = new String[6];
        travel[0] = Long.toString(id);
        travel[1] = Long.toString(syncFlag);
        travel[2] = Long.toString(date);
        travel[3] = Double.toString(hours);
        travel[4] = method;
        travel[5] = dest;
        return travel;
    }

}
