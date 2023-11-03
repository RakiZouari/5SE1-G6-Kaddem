# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container at the path
COPY target/Kaddem-2.0.0.jar .

# Expose the port that your Spring Boot application will run on
EXPOSE 8089

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "Kaddem-2.0.0.jar"]