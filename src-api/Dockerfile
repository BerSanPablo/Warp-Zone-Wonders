FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/API-VG-REVIEWS-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} wzw_api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "wzw_api.jar"]