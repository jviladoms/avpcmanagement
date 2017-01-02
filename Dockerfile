FROM tomcat:8.5-alpine
COPY ./build/libs/avpc-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/