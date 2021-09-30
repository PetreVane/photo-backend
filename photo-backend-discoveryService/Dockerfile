FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
EXPOSE 8010
COPY target/config-server-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ConfigServer.jar"]