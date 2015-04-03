package ru.semiot.simulators.heatmeter;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
    "file:/semiot-platform/simulator-heat-meters/config.properties"
})
public interface SimulatorConfig extends Config {
    
    @DefaultValue("localhost")
    @Key("hostname")
    public String hostname();
    
    @DefaultValue("59999")
    @Key("clientPort")
    public int clientPort();
    
    @DefaultValue("3")
    @Key("count")
    public int metersCount();
    
    @DefaultValue("10000")
    @Key("heartbeat")
    public int metersHeartbeat();
    
    @DefaultValue("3")
    @Key("outsideTemperature")
    public int outsideTemperature();
    
    @DefaultValue("200")
    @Key("quartersMax")
    public int quartersMax();
    
    @DefaultValue("80")
    @Key("quartersMin")
    public int quartersMin();
    
    @DefaultValue("10")
    @Key("timeToStart")
    public int timeToStart();
    
    @DefaultValue("60000")
    @Key("startPort")
    public int startPort();
    
    @DefaultValue("coap://localhost:3131/register")
    @Key("registerURI")
    public String registerURI();
    
    @DefaultValue("5")
    @Key("nbOfParallelTaks")
    public int nbOfParallelTaks();
    
}
