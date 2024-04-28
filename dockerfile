FROM gradle:8.6.0-jdk21 AS build-stage

WORKDIR /app/home/
COPY . .
RUN gradle build

FROM openjdk:21-jdk

WORKDIR /usr/src/app

COPY --from=build-stage /app/home/build/quarkus-app .

EXPOSE 8080

CMD ["java", "-jar", "/usr/src/app/quarkus-run.jar"]