package uk.ac.bradford.findmymigraine;

/**
 * Created by Imtanan on 03/03/2015.
 */
public class Causes {
    private long id, date;
    private int syncFlag, stress, lack_of_sleep, lack_of_food, lack_of_water, depression;
    private String other;

    public Causes(){}

    public Causes(long date, int stress, int lack_of_sleep, int lack_of_food, int lack_of_water, int depression, String other){

        this.date=date;
        this.stress=stress;
        this.lack_of_sleep=lack_of_sleep;
        this.lack_of_food=lack_of_food;
        this.lack_of_water=lack_of_water;
        this.depression=depression;
        this.other=other;
        this.syncFlag=0;
    }

    //Here are the setters
    public void setId(long id) {
        this.id = id;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setSyncFlag(int syncFlag) {
        this.syncFlag = syncFlag;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public void setLack_of_sleep(int lack_of_sleep){
        this.lack_of_sleep=lack_of_sleep;
    }

    public void setLack_of_food(int lack_of_food) {
        this.lack_of_food = lack_of_food;
    }

    public void setLack_of_water(int lack_of_water) {
        this.lack_of_water = lack_of_water;
    }

    public void setDepression(int depression) {
        this.depression = depression;
    }

    public void setOther(String other){

        this.other=other;
    }

    //Here are the Getters


    public long getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getSyncFlag() {
        return syncFlag;
    }

    public int getStress() {
        return stress;
    }

    public int getLack_of_sleep() {
        return lack_of_sleep;
    }

    public int getLack_of_food() {
        return lack_of_food;
    }

    public int getLack_of_water() {
        return lack_of_water;
    }

    public int getDepression() {
        return depression;
    }

    public String getOther() {
        return other;
    }

    @Override
    public String toString() {
        return "Causes [ID: "+id+ ", Date: " +date+", Stress: " +stress+", Lack of water: "+ lack_of_sleep + ", Lack of water: "+lack_of_food+", Lack of water: "+lack_of_water+", Depression: "+depression+", Other: "+other+ "]" + "\n";
    }
}
