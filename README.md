# Getting Started

This client is a microservice which is a eureka client and service discovery is handled by a eureka server

##Prerequisite

1. Java 11
2. Maven
3. Docker
4. Git

##Run Project

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

6. Test the API using the following

   curl --request GET \
   --url 'http://localhost:8001/getusercontacts?id=1'     OR

   curl --request GET \
   --url 'http://localhost:8001/getusercontacts?username=Bret'

   curl --request GET \
   --url 'http://localhost:8001/getusercontacts?id=1&username=Bret'