package ru.semiot.simulators.heatmeter;

import madkit.kernel.Madkit;

public class Launcher implements IListener {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.run();
    }

    public void run() {
        TestimonialStore.getInstance().addListener(this);
        String[] args = {
            "--launchAgents", 
            AgentScheduler.class.getName(), 
            ",false;"
        };
        Madkit.main(args);
    }

    @Override
    public void onCreated(int id) {
//        int port = start_port++;
//        final Server server = new Server(port);
//
//        final CoapResource temperature = new TemperatureResource(port, id);
//        final CoapResource heat = new HeatResource(port, id);
//        final CoapResource description = new DescriptionResource(id);
//
//        server.add(temperature);
//        server.add(heat);
//        server.add(description);
//
//        server.start();
//
//        handlers.put(id, Arrays.asList(temperature, heat));
//        System.out.println("New meter registered and available on localhost:"
//                + Integer.toString(port));

//        notifier.post(String.format(DescriptionResource.text, Integer.toString(id),
//                Integer.toString(id)), MediaTypeRegistry.TEXT_PLAIN);
    }

    @Override
    public void onUpdated(int id, Testimonial t) {
//        System.out.println("Meter with id " + id + " updated; heat = " + t.getHeat());
//        if (handlers.containsKey(id)) {
//            for (CoapResource resource : handlers.get(id)) {
//                resource.changed();
//            }
//        }
    }

}
