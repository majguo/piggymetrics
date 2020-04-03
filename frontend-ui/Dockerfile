FROM openliberty/open-liberty:kernel-java8-openj9-ubi

COPY --chown=1001:0 src/main/liberty/config /config/
COPY --chown=1001:0 target/frontend-ui.war /config/apps
COPY --chown=1001:0 init.sh /config/apps
RUN chmod +x /config/apps/init.sh

RUN configure.sh

ENTRYPOINT [ "/opt/ol/helpers/runtime/docker-server.sh" ]
CMD ["/config/apps/init.sh", "/config/apps", "frontend-ui.war"]
