From eclipse-temurin:17-jdk
# Ordner im Container festlegen
WORKDIR /app
# alle Datein ins Arbeitsverzeichnis kopiern
COPY . .
# Die Anwendu mit Gradle bauen
RUN ./gradlew clean bootJar --no-daemon
# Starte die Anwendung
CMD ["sh", "-c", "java -jar build/libs/*.jar"]
# PORT  8080 im Container öffnen
EXPOSE 8080