FROM ubuntu

WORKDIR /root

# Java and utils
RUN \
  apt-get update && \
  apt-get install -y openjdk-7-jdk && \
  rm -rf /var/lib/apt/lists/*

# Maven
RUN apt-get update
RUN apt-get install -y maven git

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

RUN git clone https://github.com/semiotproject/simulator-heat-meters.git  /root/simulator

RUN chmod 777 ./simulator/simulator-heat-meter/run.sh

CMD ./simulator/simulator-heat-meter/run.sh


