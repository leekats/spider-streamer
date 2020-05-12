FROM openjdk:8
MAINTAINER Lee Katsnelson

VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xmx4096m","-jar","/app.jar"]