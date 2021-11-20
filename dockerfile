FROM openjdk:latest

ADD target/School-API-1.jar app.jar

ENTRYPOINT [ "java" ,"-jar", "app.jar" ]

EXPOSE 8081