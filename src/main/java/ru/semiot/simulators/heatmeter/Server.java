package ru.semiot.simulators.heatmeter;

import java.util.Observable;
import java.util.Observer;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import ru.semiot.simulators.heatmeter.coap.DescriptionResource;
import ru.semiot.simulators.heatmeter.coap.HeatResource;
import ru.semiot.simulators.heatmeter.coap.TemperatureResource;

public class Server extends CoapServer implements Observer {

    private final TemperatureResource temperature;
    private final HeatResource heat;
    private final DescriptionResource description;

    public Server(int port) {
        super(port);

        temperature = new TemperatureResource(port);
        heat = new HeatResource(port, port);
        description = new DescriptionResource(port);

        add(description.add(
                new CoapResource("temperature").add(temperature),
                new CoapResource("heat").add(heat)));
    }

    @Override
    public void update(Observable o, Object arg) {
        Double[] updates = (Double[]) arg;
        temperature.setTemperature(updates[0]);
        temperature.changed();
        
        heat.setHeat(updates[1]);
        heat.changed();
    }
}
