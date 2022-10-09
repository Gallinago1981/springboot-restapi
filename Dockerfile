FROM openjdk:11.0.3-jdk-slim-stretch

COPY --from=libs app.jar app.jar

ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]