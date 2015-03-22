#!/bin/bash

APP_NAME=simulator-heat-meter-1.0-SNAPSHOT
SIMULATOR_CONFIG=https://raw.githubusercontent.com/semiotproject/simulator-heat-meters/master/simulatorConfig.xml

git pull

mvn clean package -DskipTests=true

wget ${SIMULATOR_CONFIG} -O ./simulatorConfig.xml

java -jar /root/simulator/target/${APP_NAME}.jar simulatorConfig.xml
	


