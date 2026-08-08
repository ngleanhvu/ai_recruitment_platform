FROM eclipse-temurin:23-jdk AS builder

WORKDIR /candidate-service

COPY .mvn .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

COPY src src

RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:23-jre

WORKDIR /candidate-service

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "app.jar"]