FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
#RUN gradle clean build
COPY build/libs/productService-0.0.1-SNAPSHOT.jar productService.jar
ENTRYPOINT ["java","-jar","/productService.jar"]