# Piggy Metrics on Open Liberty
## Description
It's a demo application for Open Liberty MicroProfile, which references the following major open-source projects:
 - [`piggymetrics`](https://github.com/sqshq/piggymetrics): front-end, REST APIs definition and business logics 
 - [`sample-acmegifts`](https://github.com/OpenLiberty/sample-acmegifts): architecture and OpenLiberty ways to implement micro-services
 - [`jnosql-artemis`](https://github.com/eugenp/tutorials/tree/master/persistence-modules/jnosql/jnosql-artemis): use Eclipse JNoSQL to easily interact with MongoDB
 - [`openliberty-config-example`](https://github.com/sdaschner/openliberty-config-example/tree/prometheus-k8s): use Prometheus to collect metrics data of micro-services and get them visualized/monitored in Grafana dashboard

## Technologies used
- MicroProfile 3.2 
  - Config (gateway, auth-service, account-service, statistics-service)
  - Fault Tolerance (statistics-service)
  - Health Checks (gateway, auth-service, account-service, statistics-service)
  - Metrics (gateway, auth-service, account-service, statistics-service)
  - JWT (gateway, auth-service, account-service, statistics-service)
  - Rest Client (gateway, account-service, statistics-service)
  - Open Tracing (gateway, auth-service, account-service, statistics-service)
  - Open API (gateway)
  - JAX-RS (gateway, auth-service, account-service, statistics-service)
  - JSON-B/JSON-P (gateway, auth-service, account-service, statistics-service)
  - CDI (gateway, auth-service, account-service, statistics-service)
- Java EE 8
  - Security (auth-service)
- Zipkin for distributed tracing
- Prometheus/Grafana for metric collection/visualization
- ELK for log aggregation & analysis

## Deploy and run on AKS (Azure Kubernetes Service) cluster
