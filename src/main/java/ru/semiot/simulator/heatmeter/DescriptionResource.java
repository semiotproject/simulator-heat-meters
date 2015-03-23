package ru.semiot.simulator.heatmeter;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_HTML;

public class DescriptionResource extends CoapResource {

    private final int id;
    public static final String text = ""
            +"@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n."
            + "\n"
            + "<http://example.com/resources/#meter-%s> a hmtr:HeatMeter.";

    public DescriptionResource(int id) {
        super("desc");
        this.id = id;
        getAttributes().setTitle(String.format(text, Integer.toString(id)));
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        exchange.respond(CONTENT, String.format(text, Integer.toString(id)), TEXT_HTML);
    }

}
