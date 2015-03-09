package smartmeters;

/**
 * Created by nikolay on 02.03.15.
 */

import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import static org.eclipse.californium.core.coap.CoAP.ResponseCode.*;
import static org.eclipse.californium.core.coap.CoAP.ResponseCode.DELETED;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_HTML;
import static org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN;

/**
 * Created by nikolay on 19.02.15.
 */

public class DescriptionResource extends CoapResource {

    private int id;
    private String text;

    public DescriptionResource(int _id) {
        super("");

        id = _id;
        text = "@prefix hmtr: <http://purl.org/NET/ssnext/heatmeters#>\n" +
                "\n" +
                String.format("<#meter-%s> a emtr:HeatMeter .", Integer.toString(_id));

        getAttributes().setTitle(text);
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.setMaxAge(5);
        exchange.respond(CONTENT, text, TEXT_HTML);
    }

}
