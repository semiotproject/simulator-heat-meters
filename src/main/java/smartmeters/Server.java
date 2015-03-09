package smartmeters;

import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.Resource;

/**
 * Created by Admin on 09.03.2015.
 */
public class Server extends CoapServer {

    protected TestimonialStore store  = TestimonialStore.getInstance();
    private int _id = store.getId();
    private int _port;

    public Server(int port) {
        super(port);
        _port = port;
    }
    @Override
    protected Resource createRoot() {
        return new DescriptionResource(_id);
    }
}
