FROM ubuntu

WORKDIR /root

# Java
RUN \
  apt-get update && \
  apt-get install -y openjdk-7-jdk && \
  rm -rf /var/lib/apt/lists/*

# Utils
RUN apt-get update
RUN apt-get install -y maven git wget

ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

RUN git clone https://github.com/semiotproject/simulator-heat-meters.git  ./simulator/

RUN chmod 777 ./simulator/run.sh

CMD ./simulator/run.sh


