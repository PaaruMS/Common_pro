FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/order_serv-0.0.1-SNAPSHOT.jar order_serv.jar
ENTRYPOINT ["java","-jar","/order_serv.jar"]
