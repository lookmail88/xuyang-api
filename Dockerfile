FROM docker.io/library/node:20-alpine as build
LABEL authors="xgao"

VOLUME /tmp

ARG JAR_FILE
COPY target/${JAR_FILE} /app/app.jar
ENTRYPOINT java -Duser.timezone="America/Los_Angeles" $JVM_OPTS -jar /app/app.jar
RUN chown -R 70501 /app/ && chmod -R 777 /app/
RUN chown -R 70501 /tmp/ && chmod -R 777 /tmp/

USER 70501