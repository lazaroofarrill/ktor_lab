FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir "/app"
COPY ./build/libs/*-all.jar /app/ktor-lab.jar
CMD java -jar /app/ktor-lab.jar
