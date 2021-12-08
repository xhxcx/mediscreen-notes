FROM openjdk:8-jdk-alpine
COPY target/*.jar notesApi-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/notesApi-1.0-SNAPSHOT.jar"]