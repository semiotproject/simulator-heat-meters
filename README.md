# simulator-heat-meters

We use [Owner](http://owner.aeonbits.org/) to manage application with Java Properties:
- `meters_count` - number of separately working simulators
- `meters_heartbeat` - time in ms for single meter to update testimonials
- `outside_temperature` - in C, affects to the testimonials
- `quarters_max`, `quarters_min` - range for simulator to choose a quarters count. Affect to the testimonials
- `coap_ttl` - Time To Live in ms for CoAP packages
- `time_to_start` - time interval in seconds to all the simulators launch on

Run app and open `coap://localhost:5683/.well-known/core` with [Copper](https://addons.mozilla.org/ru/firefox/addon/copper-270430/) after `time_to_start` seconds. The public CoAP interface is:
- *GET* or *OBSERVE* `/subscribe` to find all available meters, their addresses and current heat rate
- *GET* or *OBSERVE* `/subscribe/[0..$METERS-COUNT]` to find a particular meter

