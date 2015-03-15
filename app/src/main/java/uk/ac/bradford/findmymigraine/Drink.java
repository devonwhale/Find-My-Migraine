package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 12/03/2015.
 */
public class Drink {
    private long id, date;
    private int syncFlag, beer, redWine, whiteWine, spirit, soda, coffee, tea;

    //Constructor
    public Drink(){
    }
    //Constructor
    public Drink(long date, int beer, int redWine, int whiteWine, int spirit, int soda, int coffee, int tea){
        this.date=date;
        this.beer=beer;
        this.redWine=redWine;
        this.whiteWine=whiteWine;
        this.spirit=spirit;
        this.soda=soda;
        this.coffee=coffee;
        this.tea=tea;
        syncFlag = 0;
    }

    //Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setBeer(int beer) {
        this.beer = beer;
    }

    public void setWhiteWine(int whiteWine) {
        this.whiteWine = whiteWine;
    }

    public void setRedWine(int redWine) {
        this.redWine = redWine;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public void setSoda(int soda) {
        this.soda = soda;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public void setTea(int tea) {
        this.tea = tea;
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

    public int getBeer() {
        return beer;
    }

    public int getRedWine() {
        return redWine;
    }

    public int getWhiteWine() {
        return whiteWine;
    }

    public int getSpirit() {
        return spirit;
    }

    public int getSoda() {
        return soda;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getTea() {
        return tea;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    @Override
    public String toString() {
        return "Drink [ID: " + id + ", date: "+date+", beer: " + beer +  ", Red Wine: " + redWine+", White Wine"+ whiteWine+ ", spirit: "+spirit+", soda: "+soda+", coffee: " +coffee+", tea: " +tea+"]" + "\n";
    }
}
