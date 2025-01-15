FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Project-0.0.1-SNAPSHOT.jar /app/Project-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Project-0.0.1-SNAPSHOT.jar"]