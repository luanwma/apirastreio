FROM openjdk

WORKDIR /app

COPY target/tracking-api-1.0.jar /app/tracking-api.jar

ENTRYPOINT ["java", "-jar", "tracking-api.jar"]