FROM tomcat:10-jdk17
LABEL authors="Michail E. Koutrakis"

ADD target/voting-system-servlet-jsp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/voting-system-servlet-jsp-1.0-SNAPSHOT.war

CMD ["catalina.sh", "run"]