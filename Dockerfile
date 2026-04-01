FROM eclipse-temurin:25
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring

ARG JAR_FILE
COPY target/${JAR_FILE} /app/app.jar
ENTRYPOINT java -Duser.timezone="America/Los_Angeles" $JVM_OPTS -jar /app/app.jar