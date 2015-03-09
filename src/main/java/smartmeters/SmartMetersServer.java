package smartmeters;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CaliforniumLogger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.network.CoAPEndpoint;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.network.EndpointManager;
import org.eclipse.californium.core.network.EndpointObserver;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.eclipse.californium.core.network.interceptors.MessageTracer;

import madkit.kernel.Madkit;
import org.eclipse.californium.core.server.ServerMessageDeliverer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.server.resources.Resource;

/**
 * Created by nikolay on 19.02.15.
 */

public class SmartMetersServer implements IListener {

    private SmartMetersConfig simulationConfig  = ConfigFactory.create(SmartMetersConfig.class);
    private int start_port                      = simulationConfig.start_port();

    private static Map<String, CoapResource> handlers = new HashMap<String, CoapResource>();

    public static void main(String[] args) {
        new SmartMetersServer();
    }

    public SmartMetersServer() {
        TestimonialStore.getInstance().addListener(this);
        new Madkit(
                "--launchAgents",
                HeatMeterSPT943_4.class.getName() + ",false," + Integer.toString(simulationConfig.meters_count())
        );
    }

    @Override
    public void onCreated(String _id) {
        CoAPEndpoint endpoint = new CoAPEndpoint(start_port++);
        InetSocketAddress sockAddress = endpoint.getAddress();
        int port = sockAddress.getPort();
        CoapServer server = new CoapServer(port) {
            @Override
            protected Resource createRoot() {
                return new SubscribeSingle("10");
            }
        };
        CoapResource topResource = new SubscribeSingle(_id);

        server.add(topResource);
        server.start();

        handlers.put(_id, topResource);
        System.out.println("New meter registered and available on " + sockAddress.getHostName() + ":" + Integer.toString(port));
    }

    @Override
    public void onUpdated(String id, String data) {
        /*
        System.out.println("Meter with id " + id + " updated; heat = " + data);
        if (handlers.containsKey(id)) {
            handlers.get(id).changed();
        }
        */
    }



}
