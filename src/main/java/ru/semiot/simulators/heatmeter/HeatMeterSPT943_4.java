package ru.semiot.simulators.heatmeter;

import org.aeonbits.owner.ConfigFactory;

public class HeatMeterSPT943_4 extends HeatAgent {
    
    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);

    @Override
    protected Double calculateHeat() {
        // TODO: introduce normal calculation
        Double anchor = (quarters / 30.2) - (config.outsideTemperature() / 10.7);
        return randomGenerator.nextInt(10) * 0.1 * anchor;
    }

    @Override
    protected Double calculateTemperature() {
        // TODO: introduce normal calculation
        return randomGenerator.nextInt(10) * 0.2  + 65;
    }
}
