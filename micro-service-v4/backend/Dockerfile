FROM openjdk:8-jdk-alpine
MAINTAINER luigi@foogaro.com
RUN mkdir -p /app
WORKDIR /app
COPY target/backend-1.0.0.jar backend-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/backend-1.0.0.jar"]