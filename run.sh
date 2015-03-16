#!/bin/bash


APP_DIR=./simulator
APP_NAME=simulator-heat-meter-1.0-SNAPSHOT

pushd $APP_DIR

git pull

mvn clean package -DskipTests=true

wget ${SIMULATOR_CONFIG} -O ./simulatorConfig.xml

cat ./simulatorConfig.xml
#java -jar ${APP_DIR}/target/${APP_NAME}.jar

popd
	


