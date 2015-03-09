package smartmeters;

/**
 * Created by nikolay on 02.03.15.
 */

import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.DELETED;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

/**
 * Created by nikolay on 19.02.15.
 */

public class HeatResource extends CoapResource {

    private int id;
    private int port;
    private TestimonialStore store = TestimonialStore.getInstance();

    public HeatResource(int _port, int _id) {
        super("heat");

        id = _id;
        port = _port;

        setObservable(true);
        getAttributes().setTitle("Endpoint for heat testimonials ");
        getAttributes().addResourceType("observe");
        getAttributes().setObservable();
        setObserveType(CoAP.Type.CON);
    }

    private String toTurtle(double heat, long timestamp) {
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date(timestamp * 1000));

        return  "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n" +
                "@prefix meter: <http://purl.org/NET/ssnext/meters/core#>\n" +
                "\n" +
                String.format("<#heat-%s-%d> a hmtr:HeatObservation ;\n", id, timestamp) +
                String.format("    ssn:observationResultTime “%s”^^xsd:dateTime ;\n", date) +
                String.format("    ssn:observedBy <%s> ;\n", "localhost:" + Integer.toString(port)) +
                String.format("    ssn:observationResult <#heat-%s-%d-%.2f> .\n", id, timestamp, heat) +
                "\n" +
                String.format("<#heat-%s-%d-results> a hmtr:HeatSensorOutput ;\n", id, timestamp) +
                String.format("    ssn:isProducedBy <%s> ;\n", "localhost:" + Integer.toString(port)) +
                String.format("    ssn:hasValue <#heat-%s-%d-%.2f> .\n", id, timestamp, heat) +
                "\n" +
                String.format("<#heat-%s-%d-%.2f> a hmtr:HeatValue ;\n", id, timestamp, heat) +
                "    meter:hasQuantityValue “80”^^xsd:float";
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        Testimonial t = store.getData(id);
        exchange.respond(CONTENT, toTurtle(t.getHeat(), t.getTime()), TEXT_PLAIN);
    }

}
