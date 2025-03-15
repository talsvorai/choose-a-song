# Use a minimal OpenJDK image for running the Java application
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target folder into the container (Ensure the correct path)
COPY ./target/choose-a-song-0.0.1-SNAPSHOT.jar /app/choose-a-song.jar

# Expose port 8080 for the web app
EXPOSE 8080

# Set the default command to run the application
CMD ["java", "-jar", "/app/choose-a-song.jar"]