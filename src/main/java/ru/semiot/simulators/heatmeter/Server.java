package ru.semiot.simulators.heatmeter;

import java.util.Observable;
import java.util.Observer;
import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import ru.semiot.simulators.heatmeter.coap.DescriptionResource;
import ru.semiot.simulators.heatmeter.coap.HeatResource;
import ru.semiot.simulators.heatmeter.coap.TemperatureResource;

public class Server extends CoapServer implements Observer {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private final TemperatureResource temperature;
    private final HeatResource heat;
    private final DescriptionResource description;

    public Server(final int port) {
        super(port);

        temperature = new TemperatureResource(port);
        heat = new HeatResource(port, port);
        description = new DescriptionResource(port);

        add(description.add(
                new CoapResource("temperature").add(temperature),
                new CoapResource("heat").add(heat)));
    }

    @Override
    public void start() {
        super.start();
        
        if(config.registerOnStart()) {
            final CoapClient coapClient = new CoapClient(config.registerURI());
            coapClient.setEndpoint(getEndpoints().get(0));
            coapClient.post(description.getDescription(), MediaTypeRegistry.TEXT_PLAIN);
            coapClient.shutdown();
        }
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
