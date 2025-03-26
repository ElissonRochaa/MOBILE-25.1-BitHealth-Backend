# Stage 1
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean install -DskipTests

# Stage 2
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/BitHealth-*.jar backend-mobile.jar

ENV JAVA_OPTS="-Xms256m -Xmx512m"

ENTRYPOINT ["java", "-jar", "backend-mobile.jar"]

EXPOSE 8080