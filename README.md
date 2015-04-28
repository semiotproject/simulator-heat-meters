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
  

