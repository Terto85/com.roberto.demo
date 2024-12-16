FROM openjdk:23-jdk
COPY /target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
# What the container should run when it is started.
CMD java -jar /app/demo-0.0.1-SNAPSHOT.jar  com.roberto.demo.application.DemoApplication