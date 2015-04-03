package ru.semiot.simulators.heatmeter;

import madkit.kernel.Agent;
import org.aeonbits.owner.ConfigFactory;
import java.util.Random;
import java.util.logging.Level;

public class HeatAgent extends Agent {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    protected TestimonialStore store = TestimonialStore.getInstance();
    protected int id = store.getNextId();
    protected Random randomGenerator = new Random();
    protected int quarters = randomGenerator.nextInt(90) + 20;

    protected void updateTestimonial() {
        store.setData(calculateTemperature(), calculateHeat(), id);
    }

    protected Double calculateTemperature() {
        return 0.0;
    }

    protected Double calculateHeat() {
        return 0.0;
    }

    @Override
    protected void activate() {
        pause((randomGenerator.nextInt((config.timeToStart())) + 1) * 1000);
    }

    @Override
    protected void live() {
        while (true) {
            updateTestimonial();
            pause(config.metersHeartbeat());
        }
    }

    @Override
    protected void end() {
        if (logger != null) {
            logger.log(Level.INFO, "{0} stopping..", this.getName());
        }
    }
}
