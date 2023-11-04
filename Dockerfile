# Use an OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR .

# Copy the JAR file from your project into the container
COPY ./target/Kaddem-0.0.1-SNAPSHOT.jar /app/Kaddem-0.0.1-SNAPSHOT.jar

# Expose the port your Spring Boot application runs on (change to the actual port)
EXPOSE 8089


# Define the command to run your Spring Boot application
CMD ["java", "-jar", "Kaddem-0.0.1-SNAPSHOT.jar"]
