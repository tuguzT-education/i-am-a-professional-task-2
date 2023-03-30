FROM openjdk:11
EXPOSE 8080:8080
ENV DATABASE_URL jdbc:h2:mem:test;DB_CLOSE_DELAY=-1
ENV DATABASE_DRIVER org.h2.Driver
RUN mkdir /app
COPY ./build/libs/*.jar /app/web.jar
ENTRYPOINT ["java","-jar","/app/web.jar"]
