package ru.semiot.simulator.heatmeter;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

public class TemperatureResource extends CoapResource {

    private final int id;
    private final int port;
    private final TestimonialStore store = TestimonialStore.getInstance();

    public TemperatureResource(int port, int id) {
        super("temperature");

        this.id = id;
        this.port = port;

        setObservable(true);
        getAttributes().setTitle("Endpoint for temperature testimonials ");
        getAttributes().addResourceType("observe");
        getAttributes().setObservable();
        setObserveType(CoAP.Type.CON);
    }

    private String toTurtle(double temperature, long timestamp) {
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .format(new Date(timestamp * 1000));

        return "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n"
                + "@prefix meter: <http://purl.org/NET/ssnext/meters/core#>\n"
                + "\n"
                + String.format("<#temperature-%s-%d> a hmtr:TemperatureObservation ;\n", id, timestamp)
                + String.format("    ssn:observationResultTime “%s”^^xsd:dateTime ;\n", date)
                + String.format("    ssn:observedBy <%s> ;\n", "localhost:" + Integer.toString(port))
                + String.format("    ssn:observationResult <#temperature-%s-%d-%.2f> .\n", id, timestamp, temperature)
                + "\n"
                + String.format("<#temperature-%s-%d-results> a hmtr:TemperatureSensorOutput ;\n", id, timestamp)
                + String.format("    ssn:isProducedBy <%s> ;\n", "localhost:" + Integer.toString(port))
                + String.format("    ssn:hasValue <#temperature-%s-%d-%.2f> .\n", id, timestamp, temperature)
                + "\n"
                + String.format("<#temperature-%s-%d-%.2f> a hmtr:TemperatureValue ;\n", id, timestamp, temperature)
                + "    meter:hasQuantityValue “80”^^xsd:float";
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        Testimonial t = store.getData(id);
        exchange.respond(CONTENT, toTurtle(t.getTemperature(), t.getTime()), TEXT_PLAIN);
    }

}
