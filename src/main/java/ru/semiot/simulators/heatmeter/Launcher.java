package ru.semiot.simulators.heatmeter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import madkit.kernel.Madkit;

public class Launcher implements IListener {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private static final Map<Integer, List<CoapResource>> handlers = new HashMap<>();
    private final CoapClient notifier = new CoapClient(config.registerURI());
    private int start_port = config.startPort();

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.run(args);
    }

    public void run(final String[] args) {
        TestimonialStore.getInstance().addListener(this);
        new Madkit(
                "--launchAgents",
                HeatMeterSPT943_4.class.getName() + ",false,"
                + Integer.toString(config.metersCount())
        );
    }

    @Override
    public void onCreated(int _id) {
        int port = start_port++;
        Server server = new Server(port);

        CoapResource temperature = new TemperatureResource(port, _id);
        CoapResource heat = new HeatResource(port, _id);

        server.add(temperature);
        server.add(heat);
        server.add(new DescriptionResource(_id));

        server.start();

        handlers.put(_id, Arrays.asList(temperature, heat));
        System.out.println("New meter registered and available on localhost:"
                + Integer.toString(port));

        notifier.post(String.format(DescriptionResource.text, Integer.toString(_id),
                Integer.toString(_id)), MediaTypeRegistry.TEXT_PLAIN);
    }

    @Override
    public void onUpdated(int id, Testimonial t) {
        System.out.println("Meter with id " + id + " updated; heat = " + t.getHeat());
        if (handlers.containsKey(id)) {
            for (CoapResource resource : handlers.get(id)) {
                resource.changed();
            }
        }
    }

}
