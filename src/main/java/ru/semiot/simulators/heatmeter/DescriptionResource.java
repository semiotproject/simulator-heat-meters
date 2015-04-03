package ru.semiot.simulators.heatmeter;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_HTML;

public class DescriptionResource extends CoapResource {

    private final int id;
    public static final String text = ""
            + "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>.\n"
            + "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>.\n"
            + "\n"
            + "<http://example.com/resources/#meter-%s> a hmtr:HeatMeter;\n"
            + "rdfs:label \"Heat_Meter_№_%s\".";

    public DescriptionResource(int id) {
        super("desc");
        this.id = id;
        getAttributes().setTitle(String.format(text, Integer.toString(id), Integer.toString(id)));
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        exchange.respond(CONTENT, String.format(text, Integer.toString(id), Integer.toString(id)), TEXT_HTML);
    }

}
