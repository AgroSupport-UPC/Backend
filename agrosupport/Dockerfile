FROM openjdk:22-jdk
ARG JAR_FILE=target/api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} agrosupport.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/agrosupport.jar"]