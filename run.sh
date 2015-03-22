#!/bin/bash

APP_DIR=/root/simulator/
APP_NAME=simulator-heat-meters-1.0-SNAPSHOT
SIMULATOR_CONFIG=https://raw.githubusercontent.com/semiotproject/simulator-heat-meters/master/simulatorConfig.xml

pushd $APP_DIR

git pull

mvn clean package -DskipTests=true

wget ${SIMULATOR_CONFIG} -O ./simulatorConfig.xml

java -jar ./target/${APP_NAME}.jar simulatorConfig.xml

popd
	


