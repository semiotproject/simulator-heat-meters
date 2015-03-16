package ru.semiot.simulator.heatmeter;

import static ru.semiot.simulator.heatmeter.SmartMetersConfig.conf;

public class HeatMeterSPT943_4 extends HeatAgent {

    @Override
    protected Double calculateHeat() {
        // TODO: introduce normal calculation
        Double anchor = (quarters / 30.2) - (conf.getOutsideTemperature() / 10.7);
        return randomGenerator.nextInt(10) * 0.1 * anchor;
    }

    @Override
    protected Double calculateTemperature() {
        // TODO: introduce normal calculation
        return randomGenerator.nextInt(10) * 0.2  + 65;
    }
}
