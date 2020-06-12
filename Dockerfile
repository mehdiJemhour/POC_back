FROM openjdk:8-jdk-alpine as builder
WORKDIR /backend/app
COPY . .
RUN ./mvnw -q clean package

FROM builder
COPY --from=builder ./backend/app/target/*.jar bdx-import.jar
ENTRYPOINT ["java","-jar","bdx-import.jar"]