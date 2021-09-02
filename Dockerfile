FROM openjdk:latest as builder
ADD target/client-0.0.1-SNAPSHOT.jar user-client.jar
FROM dannkhoma/identity:latest as runner
RUN docker -d -p 8000:8000 dannkhoma/identity
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/user-client.jar"]