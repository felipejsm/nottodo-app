FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} nottodo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/nottodo.jar"]