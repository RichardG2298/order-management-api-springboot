FROM openjdk:27-ea-17-jdk-slim

WORKDIR /app

COPY target/order-management-api-4.0.5.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]