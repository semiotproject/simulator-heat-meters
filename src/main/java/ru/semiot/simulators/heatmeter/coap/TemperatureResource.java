package ru.semiot.simulators.heatmeter.coap;

import java.io.IOException;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.IOUtils;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;
import ru.semiot.simulators.heatmeter.SimulatorConfig;

public class TemperatureResource extends CoapResource {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private final int port;
    private double temperature;
    private long timestamp;
    private final String template;

    public TemperatureResource(int port) {
        super("obs");

        this.port = port;

        setObservable(true);
        getAttributes().setObservable();
        setObserveType(CoAP.Type.CON);

        try {
            this.template = IOUtils.toString(TemperatureResource.class.getResourceAsStream(
                    "/ru/semiot/simulators/heatmeter/temperature.ttl"));
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
        this.timestamp = System.currentTimeMillis();
    }

    private String toTurtle(double temperature, long timestamp) {
        final String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .format(new Date(timestamp));
        return template
                .replace("${HOST}", config.hostname())
                .replace("${PORT}", String.valueOf(port))
                .replace("${TIMESTAMP}", String.valueOf(timestamp))
                .replace("${DATETIME}", date)
                .replace("${VALUE}", String.valueOf(temperature));
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CONTENT, toTurtle(temperature, timestamp), TEXT_PLAIN);
    }

}
