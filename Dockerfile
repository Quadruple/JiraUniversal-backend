FROM openjdk:11-jre-slim

COPY build/libs/jiraservice-0.0.1-SNAPSHOT.jar /opt/app/jiraservice-0.0.1-SNAPSHOT.jar

CMD java -jar /opt/app/jiraservice-0.0.1-SNAPSHOT.jar