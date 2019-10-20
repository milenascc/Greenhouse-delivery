FROM openjdk:8-jdk-alpine
COPY payment-service-1.0.0-SNAPSHOT.jar payment-service.jar
EXPOSE 8004
ENTRYPOINT [ "java", "-jar", "/payment-service.jar" ]