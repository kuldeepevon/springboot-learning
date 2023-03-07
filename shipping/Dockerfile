FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
#RUN gradle clean build
COPY build/libs/sample-0.0.1-SNAPSHOT.jar sample-app.jar
ENTRYPOINT ["java","-jar","/sample-app.jar"]