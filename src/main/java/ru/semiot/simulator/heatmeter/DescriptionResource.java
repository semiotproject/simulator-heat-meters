package ru.semiot.simulator.heatmeter;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_HTML;

public class DescriptionResource extends CoapResource {

    private final int id;
    private final String text;

    public DescriptionResource(int id) {
        super("");
        this.id = id;
        this.text = "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n"
                + "\n"
                + String.format("<#meter-%s> a emtr:HeatMeter .", Integer.toString(id));
        getAttributes().setTitle(text);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        exchange.respond(CONTENT, text, TEXT_HTML);
    }

}
