FROM quay.io/wildfly/wildfly:latest

RUN /opt/jboss/wildfly/bin/add-user.sh admin rootadmin --silent

ADD --chmod=0755 wildfly-init-config.sh /opt/jboss/wildfly/bin

ADD ./server/target/tech11-task.war /opt/jboss/wildfly/

CMD ["/opt/jboss/wildfly/bin/wildfly-init-config.sh"]
