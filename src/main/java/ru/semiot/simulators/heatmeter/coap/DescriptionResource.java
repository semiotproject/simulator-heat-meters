package ru.semiot.simulators.heatmeter.coap;

import java.io.IOException;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.IOUtils;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import ru.semiot.simulators.heatmeter.SimulatorConfig;

public class DescriptionResource extends CoapResource {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private static final String DESCRIPTION_FILE = 
            "/ru/semiot/simulators/heatmeter/description_SPT943_4.ttl";
    private static final String DESCRIPTION_TEMPLATE;
    private final String description;
    
    static {
        try {
            DESCRIPTION_TEMPLATE = IOUtils.toString(DescriptionResource.class
                    .getResourceAsStream(DESCRIPTION_FILE));
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public DescriptionResource(int port) {
        super("meter");
        this.description = DESCRIPTION_TEMPLATE
                .replace("${HOST}", config.hostname())
                .replace("${PORT}", String.valueOf(port));
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.CONTENT, description);
    }

}
