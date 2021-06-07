FROM openjdk:11-jdk-slim-buster

COPY . /code
WORKDIR /code

RUN chmod +x gradlew
RUN ./gradlew build