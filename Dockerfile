FROM openjdk:11-jdk-slim-buster

COPY . /code
WORKDIR /code

ENV spring_profiles_active=test
RUN chmod +x gradlew
RUN ./gradlew build