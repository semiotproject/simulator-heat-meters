package ru.semiot.simulator.heatmeter;

public interface IListener {
    public void onCreated(int id);
    public void onUpdated(int id, Testimonial data);
}
