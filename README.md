# Getting Started

This client is a microservice which is a eureka client and service discovery is handled by a eureka server

##Prerequisite

1. Java 11
2. Maven
3. Docker
4. Git

##Run Project Manually

1. Pull docker image for the eureka server project by running

   docker pull dannkhoma/identity

2. Run the eureka server by command

   docker run -d -p 8000:8000 dannkhoma/identity

3. Clone the project by 

   git clone https://github.com/dannkhoma/client.git

4. From the root folder run 
   
   ./mvnw clean install

5. Run the project using 
   
   ./mvnw spring-boot:run

##Run Project Using Docker