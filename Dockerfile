FROM openjdk:17-alpine

WORKDIR /app

COPY target/tracking-api-1.0.jar /app/tracking-api.jar

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "tracking-api.jar"]