package ru.semiot.simulator.heatmeter;

import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.Resource;

public class Server extends CoapServer {

    protected TestimonialStore store  = TestimonialStore.getInstance();
    private final int id = store.getId();
    private final int port;

    public Server(int port) {
        super(port);
        this.port = port;
    }
}
