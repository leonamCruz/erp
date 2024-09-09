FROM maven:3.9.9-eclipse-temurin-21-alpine as build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . .
RUN mvn package

FROM eclipse-temurin:21-jre-ubi9-minimal
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/erp-0.0.1-SNAPSHOT.jar erp.jar

CMD ["sh", "-c", "sleep 8 && java -jar erp.jar"]

EXPOSE 8080
