package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 05/03/2015.
 */
public class Travel {

    private long id;
    private double hours;
    private String method, dest;

    public Travel() {

    }

    public Travel(double nHours, String nMethod, String nDest) {
        hours = nHours;
        method = nMethod;
        dest = nDest;
    }

    public void setId(long nId) {
        id = nId;
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
        return "Travel [ID: " + id + ", Hours: " + hours + ", Method: " + method + ", Destination:" + dest + "]" + "\n";
    }

}
