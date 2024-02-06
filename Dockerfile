FROM openjdk:17

WORKDIR /app

COPY build/dependencies /app/lib
COPY build/libs/* /app

ENTRYPOINT ["java", "-cp", "*:lib/*", "com.mlp.simpleaction.MainKt"]
