package smartmeters;

/**
 * Created by Admin on 09.03.2015.
 */
public class Testimonial {

    private Double temperature;
    private Double heat;
    private long time;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double t) {
        temperature = t;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(Double h) {
        heat = h;
    }

    public long getTime() {
        return time;
    }

    public void update(Double t, Double h) {
        time = System.currentTimeMillis() / 1000L;
        temperature = t;
        heat = h;
    }

}
