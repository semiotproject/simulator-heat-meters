#!/bin/bash

ls -a

APP_DIR=./simulator/simulator-heat-meter
APP_NAME=simulator-heat-meter-1.0-SNAPSHOT

pushd $APP_DIR

git pull

mvn clean package -DskipTests=true

ls ./target
#java -jar ${APP_DIR}/target/${APP_NAME}.jar

popd
	


