FROM openjdk:latest as builder
ADD target/client-0.0.1-SNAPSHOT.jar user-client.jar
FROM dannkhoma/identity AS runner
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/user-client.jar"]