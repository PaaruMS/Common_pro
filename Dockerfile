FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/user_serv-0.0.1-SNAPSHOT.jar user_serv.jar
ENTRYPOINT ["java","-jar","/user_serv.jar"]
