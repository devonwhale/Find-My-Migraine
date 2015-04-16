package uk.ac.bradford.findmymigraine;

/**
 * Created by George on 16/04/2015.
 */
public class Intensity {
    private long id;
    private String intensity;

    public Intensity() {

    }

    /**
     *
     * @param nIntensity The String associated with the intensity. It should be a string of 16 binary values.
     */
    public Intensity(String nIntensity) {
        intensity = nIntensity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String toString() {
        return "Intensity [ID: " + id + ", Intensity: " + intensity + "]" + "\n";
    }
}
