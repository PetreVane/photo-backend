FROM amazoncorretto:11-alpine-jdk
VOLUME /tmp
EXPOSE 8012
COPY src/main/resources/apiEncryptionKey.jks apiEncryptionKey.jks
COPY asymmetric-encryption/UnlimitedJCEPolicyJDK8/* /usr/lib/jvm/java-11-amazon-corretto/lib/security
COPY target/config-server-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ConfigServer.jar"]