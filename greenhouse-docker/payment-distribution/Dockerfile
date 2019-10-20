FROM openjdk:8-jdk-alpine
COPY payment-distribution-1.0.0-SNAPSHOT.jar payment-distribution.jar
EXPOSE 8003
ENTRYPOINT [ "java", "-jar", "/payment-distribution.jar" ]
