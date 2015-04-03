package ru.semiot.simulators.heatmeter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import madkit.kernel.Scheduler;
import org.aeonbits.owner.ConfigFactory;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.network.CoAPEndpoint;
import ru.semiot.simulators.heatmeter.agents.HeatAgent;

public class AgentScheduler extends Scheduler {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(
            config.nbOfParallelTaks());
    private CoapClient coapClient;

    @Override
    protected void activate() {     
        coapClient = new CoapClient(config.registerURI());
        coapClient.setEndpoint(new CoAPEndpoint(59999));
        
        for (int port = config.startPort(); 
                port < config.startPort() + config.metersCount(); port++) {
            executor.submit(new Runner(this, coapClient, port));
        }
    }

    @Override
    protected void end() {
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        executor.shutdownNow();
    }
    
    private class Runner implements Runnable {

        private final Scheduler scheduler;
        private final int port;
        private final CoapClient coapClient;
        
        public Runner(final Scheduler scheduler, final CoapClient coapClient, 
                final int port) {
            this.scheduler = scheduler;
            this.port = port;
            this.coapClient = coapClient;
        }

        @Override
        public void run() {
            final Server server = new Server(coapClient, port);
            
            scheduler.launchAgent(new HeatAgent(server));
            
            server.start();
        }
        
    }

}
