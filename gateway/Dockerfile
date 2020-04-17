FROM openliberty/open-liberty:kernel-java8-openj9-ubi

COPY --chown=1001:0 target/liberty/wlp/usr/extension /liberty/usr/extension/
COPY --chown=1001:0 src/main/liberty/config /config/
COPY --chown=1001:0 target/liberty/wlp/usr/servers/gateway/bootstrap.properties /config
COPY --chown=1001:0 target/gateway.war /config/apps
COPY --chown=1001:0 init.sh /config
RUN chmod +x /config/init.sh

RUN configure.sh

ENTRYPOINT [ "/opt/ol/helpers/runtime/docker-server.sh" ]
CMD ["/config/init.sh", "/config", "/config/apps", "gateway.war"]
