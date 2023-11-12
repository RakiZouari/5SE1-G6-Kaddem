FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/Kaddem-1.0.0.jar .
EXPOSE 8089
CMD ["java", "-jar", "Kaddem-1.0.0.jar"]
