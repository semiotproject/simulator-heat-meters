# simulator-heat-meters

How to launch the application using [Docker](https://www.docker.com/):

* Pull the image:
```bash
sudo docker pull semiot/simulator-heat-meters
```
* Run the container:
```bash
sudo docker run \
-i -t \
-v /semiot-platform/simulator-heat-meters:/semiot-platform/simulator-heat-meters \
--expose=60000-61000/udp \
semiot/simulator-heat-meters
```

How to check simulators are working:

Open in [Copper](https://addons.mozilla.org/ru/firefox/addon/copper-270430/) next URI: `coap://localhost:${[startPort..startPort + count]}/.well-known/core` and "observe" registrations on `coap://localhost:${[startPort..startPort + count]}/temperature`. 

How to manage simulators' configuration:

Create Java properties file `/semiot-platform/simulator-heat-meters/config.properties` with next parameters:

  * `hostname` - ip or name of simulators' base host, using in meter description 
  * `count` - number of launching simulators
  * `heartbeat` - time interval between meters' registration, ms
  * `outsideTemperature` - affects registration, C
  * `quartersMax` and `quartersMin` - interval of allowed quarters count in house
  * `timeToStart` - time interval to launch up all simulators, s
  * `startPort` - initial port for simulators; ports `[${startPort}..${startPort + count}]` would be occupied
  * `registerURI` - URI of available Device Proxy Service

