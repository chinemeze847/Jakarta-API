#!/bin/sh

#start wildfly
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 &

#install the driver and the data source
#the challenge is to have the server ready---it may take a while
RC=1
count=0
while [ $RC -ne 0 ] && [ $count -lt 5 ]
do
    sleep 5
    /opt/jboss/wildfly/bin/jboss-cli.sh "connect","deploy --force --url=https://jdbc.postgresql.org/download/postgresql-42.5.0.jar","data-source add --name=PostgresDS --driver-name=postgresql-42.5.0.jar  --driver-class=org.postgresql.Driver --jndi-name=java:jboss/datasources/PostgresDS --connection-url=jdbc:postgresql://host.docker.internal/tech11-task  --user-name=postgres --password=postgres","deploy /opt/jboss/wildfly/tech11-task.war"
    RC=$?
    let count++
done

#keep running forever
tail -f /dev/null

