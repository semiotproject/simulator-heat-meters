FROM fedora

ENV APP_DIR=/simulator-heat-meters
ENV APP_JAR=simulator-heat-meters-1.0-SNAPSHOT-jar-with-dependencies.jar

RUN yum update && yum install -y wget binutils unzip java-1.8.0-openjdk-devel \
yum install -y maven git

RUN git clone https://github.com/semiotproject/simulator-heat-meters.git

WORKDIR $APP_DIR

RUN mvn clean install -DskipTests=true && rm -rf ~/.m2/repository

CMD java -jar target/$APP_JAR
