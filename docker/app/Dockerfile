FROM amd64/adoptopenjdk:11.0.5_10-jdk-openj9-0.17.0-bionic

ARG APP_VERSION

ADD deploy/artifact/Tunturi-$APP_VERSION.jar Tunturi.jar

EXPOSE 10300

CMD ["java","-jar","Tunturi.jar"]
