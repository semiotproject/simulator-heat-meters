package smartmeters;

/**
 * Created by nikolay on 02.03.15.
 */
public interface IListener {
    public void onCreated(int id);
    public void onUpdated(int id, Testimonial data);
}
