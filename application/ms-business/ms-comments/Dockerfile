FROM maven:3.6.3-openjdk-14-slim AS build
COPY . .
RUN mvn clean install package -DskipTests

FROM openjdk:14-jdk-alpine
RUN apk add --no-cache bash
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
COPY --from=build /target/ms-comments-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80