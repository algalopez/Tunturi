FROM amd64/adoptopenjdk:11.0.5_10-jdk-openj9-0.17.0-bionic

ARG APP_VERSION

ADD artifact/bin/Tunturi-$APP_VERSION.jar Tunturi.jar

CMD ["java","-jar","Tunturi.jar"]
