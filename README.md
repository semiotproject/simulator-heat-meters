# simulator-heat-meters

We use [Owner](http://owner.aeonbits.org/) to manage application with Java Properties:
- `meters_count` - number of separately working simulators
- `time_to_start` - time interval in seconds to all the simulators launch on
- `start_port` - initial port to host simulators
Run app and open `coap://localhost:[$START_PORT..$START_PORT + $METERS_COUNT]/.well-known/core` with [Copper](https://addons.mozilla.org/ru/firefox/addon/copper-270430/) after `time_to_start` ms. 

- `/temperature` - get temperature in Turtle
- `/heat` - get calculated heat in Turtle
- `/` - get meter description in Turtle

