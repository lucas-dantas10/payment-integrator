FROM amazoncorretto:23-alpine AS build
WORKDIR /app

RUN apk add --no-cache tar

COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
