FROM openjdk:17

WORKDIR /app

COPY target/mlp-java-service-template/lib /app/lib
COPY target/mlp-java-service-template-* /app

ENTRYPOINT ["java", "-cp", "*:lib/*", "com.mlp.simpleaction.MainKt"]
