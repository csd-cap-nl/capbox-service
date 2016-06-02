FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD application-1.0-SNAPSHOT.jar application.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application.jar"]
