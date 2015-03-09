package smartmeters;

/**
 * Created by nikolay on 02.03.15.
 */

public class HeatMeterSPT943_4 extends HeatAgent {

    @Override
    protected Double calculateHeat() {
        // TODO: introduce normal calculation
        Double anchor = (quarters / 30.2) - (simulationConfig.outside_temperature() / 10.7);
        return randomGenerator.nextInt(10) * 0.1 * anchor;
    }

    protected Double calculateTemperature() {
        // TODO: introduce normal calculation
        return randomGenerator.nextInt(10) * 0.2  + 65;
    }
}
