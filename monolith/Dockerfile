FROM openjdk:8-jdk-alpine
MAINTAINER luigi@foogaro.com
RUN mkdir -p /app
WORKDIR /app
COPY target/monolith-1.0.0.jar monolith-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/monolith-1.0.0.jar"]