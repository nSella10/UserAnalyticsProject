FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew && ./gradlew build

CMD ["java", "-jar", "build/libs/backend-0.0.1-SNAPSHOT.jar"]
