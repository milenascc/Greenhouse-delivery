FROM openjdk:8-jdk-alpine
COPY restaurant-service-1.0.0-SNAPSHOT.jar restaurant.jar
EXPOSE 8001
ENTRYPOINT [ "java", "-jar", "/restaurant.jar" ]