# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Install curl
RUN apt-get update && apt-get install -y curl

# Set the working directory in the container
WORKDIR /app

# Specify the Nexus repository URL and the Maven coordinates of your artifact
ARG NEXUS_REPO_URL=http://localhost:8081/repository/maven-releases/
ARG GROUP_ID=com.spring
ARG ARTIFACT_ID=Kaddem
ARG VERSION=1.2

# Download the JAR file from Nexus using curl
RUN curl -o ${ARTIFACT_ID}-${VERSION}.jar "$NEXUS_REPO_URL/$GROUP_ID/$ARTIFACT_ID/$VERSION/${ARTIFACT_ID}-${VERSION}.jar"

# Expose the port that your Spring Boot application will run on
EXPOSE 8089

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "${ARTIFACT_ID}-${VERSION}.jar"]
