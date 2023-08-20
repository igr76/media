FROM openjdk:11.0.11-jre-slim
ADD /target/media-0.0.1-SNAPSHOT.jar media-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","media-0.0.1-SNAPSHOT.jar"]
