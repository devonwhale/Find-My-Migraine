package uk.ac.bradford.findmymigraine;

/**
 * Created by Sumaia on 15/03/2015.
 */
public class Food {
   private long id, date;
   private int syncFlag, chocolate, cheese, nuts, citrusFruits;

    //Constructor
    public Food(){

    }
    //Constructor
    public Food(long date, int chocolate, int cheese, int nuts, int citrusFruits) {
        this.date = date;
        this.chocolate = chocolate;
        this.cheese = cheese;
        this.nuts = nuts;
        this.citrusFruits = citrusFruits;
        syncFlag = 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    public void setChocolate(int chocolate) {
        this.chocolate = chocolate;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    public void setNuts(int nuts) {
        this.nuts = nuts;
    }

    public void setCitrusFruits(int citrusFruits) {
        this.citrusFruits = citrusFruits;
    }

    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public int getChocolate() {
        return chocolate;
    }

    public int getCheese() {
        return cheese;
    }

    public int getNuts() {
        return nuts;
    }

    public int getCitrusFruits() {
        return citrusFruits;
    }

    @Override
    public String toString() {
        return "Drink [ID: " + id + ", date: "+date+", Chocolate: " + chocolate + ", Cheese: " + cheese +", Nuts: " + nuts+", Citrus Fruits: "+citrusFruits+"]" + "\n";
    }
}
