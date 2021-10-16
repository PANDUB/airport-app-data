FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/airport-app-data-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} airport-app-data-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","airport-app-data-0.0.1-SNAPSHOT.jar"]