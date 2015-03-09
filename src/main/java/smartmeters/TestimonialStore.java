package smartmeters;

import org.aeonbits.owner.ConfigFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by nikolay on 19.02.15.
 */

public class TestimonialStore {

    private List<IListener> listeners = new ArrayList<IListener>();
    private static Map<Integer,Testimonial> data = new HashMap<Integer,Testimonial>();

    private static TestimonialStore instance = new TestimonialStore();
    private int current_id = 0;

    public TestimonialStore() {}
    public static TestimonialStore getInstance() {
        return instance;
    }

    public int getNextId() {
        return current_id++;
    }

    public int getId() {
        return current_id;
    }

    public void addListener(IListener listener) {
        listeners.add(listener);
    }

    public Testimonial getData(int id) {
        if (data.containsKey(id)) {
            return data.get(id);
        } else {
            return null;
        }
    }

    public void setData(double _temperature, double _heat, int _id) {
        Testimonial t;
        if (!data.containsKey(_id)) {
            for (IListener l : listeners) {
                l.onCreated(_id);
            }
            data.put(_id, new Testimonial());
        }
        t = data.get(_id);
        t.update(_temperature, _heat);
        for (IListener l : listeners) {
            l.onUpdated(_id, t);
        }
    }
}
