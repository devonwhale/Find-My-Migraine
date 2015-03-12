package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 05/03/2015.
 * Date field added by Steve on 12/3/15.
 */
public class Travel {

    private long id, date;
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

    public String toString() {
        return "Travel [ID: " + id + ", date: "+date+", Hours: " + hours + ", Method: " + method + ", Destination:" + dest + "]" + "\n";
    }

}
