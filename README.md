# school-api-docker
School Api spring boot and docker with DB and unit tests and integration tests

 > This is  Template Example For Rest API for  School to Register Student to course
 
 > Can create update delete or get all courses or get one

 > Can create update delete or get all student or get one
 
 > Admins can see reports for studens and courses
 
 > Students can register to course
 
 > Note -> Max students per course limit to 50 student and student limit to 5 courses

# Entry points
http://localhost:8080/api/swagger-ui-school-api.html  
This is the Swagger-UI to explore our API's and can play or give it a Test  

# Based on Spring Boot and Spring security and swagger-ui
 - Spring boot 2.6 
 - open-api / swagger-ui 
 - Unit tests and Integration tests
 - Spring Data ( JPA )
 - DB -> ( H2 in Memory DB for Integration tests / and MySql as defualt )
 - JDK 11
 
 # How to use or
 ###  USERS
  > Admin user -> UserName: admin / Password: admin
  
  > Normal user -> UserName: user / Password: user
 ###  In Dataase
 >  Inserted 5 users

 >  Inserted 5 Courses
 
 
# Can try it with 
```
 mvn spring-boot:run
 ```
 OR
 ```
  ./mvnw spring-boot:run
 ```
 
 # To Build Docker and Compose
 > We Have to package our jar file
 ```
    mvn clean compile package -DskipTests
 ```
 > Then to generate Docker Image
 ```
 docker build -t spring-boot-school-api .
 ```
 > To build Compose file with
 ```
   docker-compose up -d
 ```
 > To delete the contianers 
 ```
 docker-compose down --volumes
 ```
 
 
 > Thanks :+1: if you have any comments to share with me :shipit:  
 > By @Ismail Shebl
