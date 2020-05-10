FROM maven:3.6.3-openjdk-14-slim AS build
COPY . .
RUN mvn clean install package -DskipTests

FROM openjdk:14-jdk-alpine
COPY --from=build /target/ms-config-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80