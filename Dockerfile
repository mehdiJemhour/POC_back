FROM openjdk:8-jdk-alpine as builder
ARG jar_name
WORKDIR /backend/app
COPY . .
RUN ./mvnw clean package

FROM builder
COPY --from=builder ./backend/app/target/*.jar $jar_name.jar
ENTRYPOINT ["java","-jar","$jar_name.jar"]