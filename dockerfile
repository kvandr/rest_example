FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.7_10_openj9-0.20.0-alpine-slim
RUN apk add --no-cache fontconfig ttf-dejavu
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]