FROM ubuntu:16.04

RUN mkdir -p /motherfuckin/starboy && \
    apt-get update && \
    apt-get install -y openjdk-8-jre

COPY target/noize-jar-with-dependencies.jar /motherfuckin/starboy/noize.jar

CMD ["java", "-jar", "/motherfuckin/starboy/noize.jar"]
