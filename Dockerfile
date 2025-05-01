FROM eclipse-temurin
WORKDIR /opt/app
COPY build/libs/DeliveryService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]