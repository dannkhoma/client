FROM openjdk:latest as builder
ADD target/client-0.0.1-SNAPSHOT.jar user-client.jar
RUN docker pull dannkhoma/identity
CMD docker run -d -p 8000:8000 dannkhoma/identity
EXPOSE 8001
ENTRYPOINT ["java", "-jar", "/user-client.jar"]