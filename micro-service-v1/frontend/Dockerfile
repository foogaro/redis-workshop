FROM openjdk:8-jdk-alpine
MAINTAINER luigi@foogaro.com
RUN mkdir -p /app
WORKDIR /app
COPY target/frontend-1.0.0.jar frontend-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/frontend-1.0.0.jar"]