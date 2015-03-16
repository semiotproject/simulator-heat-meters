#!/bin/bash

APP_DIR=/root/simulator/simulator-heat-meter
APP_NAME=simulator-heat-meter-1.0-SNAPSHOT

pushd $APP_DIR

git pull

mvn clean package -DskipTests=true
java -jar ${APP_DIR}/target/${APP_NAME}.jar

popd
	


