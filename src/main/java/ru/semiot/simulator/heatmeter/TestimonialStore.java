package ru.semiot.simulator.heatmeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestimonialStore {

    private static final Map<Integer, Testimonial> data = new HashMap<>();
    private static final TestimonialStore instance = new TestimonialStore();
    private final List<IListener> listeners = new ArrayList<>();
    private int current_id = 0;

    public TestimonialStore() {
    }

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

    public void setData(double temperature, double heat, int id) {
        Testimonial t;
        if (!data.containsKey(id)) {
            for (IListener l : listeners) {
                l.onCreated(id);
            }
            data.put(id, new Testimonial());
        }
        t = data.get(id);
        t.update(temperature, heat);
        for (IListener l : listeners) {
            l.onUpdated(id, t);
        }
    }
}
