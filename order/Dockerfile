FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
#RUN gradle clean build
COPY build/libs/orderService-0.0.1-SNAPSHOT.jar orderService.jar
ENTRYPOINT ["java","-jar","/orderService.jar"]