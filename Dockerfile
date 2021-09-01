FROM openjdk:latest
ADD target/client-0.0.1-SNAPSHOT.jar user-client.jar
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/user-client.jar"]