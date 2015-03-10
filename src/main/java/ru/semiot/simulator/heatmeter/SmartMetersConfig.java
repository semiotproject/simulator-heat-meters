package ru.semiot.simulator.heatmeter;

import org.aeonbits.owner.Config;

public interface SmartMetersConfig extends Config {
    int meters_count();
    int meters_heartbeat();
    int quarters_max();
    int quarters_min();
    int outside_temperature();
    int coap_ttl();
    int time_to_start();
    int start_port();
}
