package ru.semiot.simulators.heatmeter.agents;

import java.util.Observer;
import madkit.kernel.Agent;
import org.aeonbits.owner.ConfigFactory;
import java.util.Random;
import java.util.logging.Level;
import ru.semiot.simulators.heatmeter.SimulatorConfig;

public class HeatAgent extends Agent {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private final Observer observer;
    private final Random randomGenerator = new Random();
    private final int quarters = randomGenerator.nextInt(90) + 20;
    
    public HeatAgent(final Observer observer) {
        this.observer = observer;
    }

    protected Double calculateTemperature() {
        // TODO: introduce normal calculation
        return randomGenerator.nextInt(10) * 0.2 + 65;
    }

    protected Double calculateHeat() {
        // TODO: introduce normal calculation
        Double anchor = (quarters / 30.2) - (config.outsideTemperature() / 10.7);
        return randomGenerator.nextInt(10) * 0.1 * anchor;
    }

    @Override
    protected void activate() {
        pause((randomGenerator.nextInt((config.timeToStart())) + 1) * 1000);
    }

    @Override
    protected void live() {
        while (true) {
            final double temperature = calculateTemperature();
            final double heat = calculateHeat();
            
            observer.update(null, new Double[] {temperature, heat});
            
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
