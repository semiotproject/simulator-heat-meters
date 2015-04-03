package ru.semiot.simulators.heatmeter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import madkit.kernel.Scheduler;
import org.aeonbits.owner.ConfigFactory;
import ru.semiot.simulators.heatmeter.agents.HeatAgent;

public class AgentScheduler extends Scheduler {

    private static final SimulatorConfig config = ConfigFactory.create(
            SimulatorConfig.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(
            config.nbOfParallelTaks());

    @Override
    protected void activate() {        
        for (int port = config.startPort(); 
                port < config.startPort() + config.metersCount(); port++) {
            executor.submit(new Runner(this, port));
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
        
        public Runner(final Scheduler scheduler, final int port) {
            this.scheduler = scheduler;
            this.port = port;
        }

        @Override
        public void run() {
            final Server server = new Server(port);
            
            scheduler.launchAgent(new HeatAgent(server));
            
            server.start();
        }
        
    }

}
