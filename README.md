# Piggy Metrics on Open Liberty

## Description

It's a demo application for Open Liberty MicroProfile, which references the following major open-source projects:

- [`piggymetrics`](https://github.com/sqshq/piggymetrics): front-end, REST APIs definition and business logics 
- [`sample-acmegifts`](https://github.com/OpenLiberty/sample-acmegifts): architecture and OpenLiberty ways to implement micro-services
- [`jnosql-artemis`](https://github.com/eugenp/tutorials/tree/master/persistence-modules/jnosql/jnosql-artemis): use Eclipse JNoSQL to easily interact with MongoDB
- [`openliberty-config-example`](https://github.com/sdaschner/openliberty-config-example/tree/prometheus-k8s): use Prometheus to collect metrics data of micro-services and get them visualized/monitored in Grafana dashboard

## Technologies used

- Eclipse MicroProfile 3.2
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
- Eclipse JNoSQL
  - Artemis (auth-service, account-service, statistics-service)
- Zipkin for distributed tracing
- Prometheus/Grafana for metric collection/visualization
- ELK for log aggregation & analysis

## Run it on AKS (Azure Kubernetes Service) cluster

### Prerequisites

- Register an [Azure subscription](https://azure.microsoft.com/)
- [Create service principals for AKS](https://docs.microsoft.com/azure/aks/kubernetes-service-principal#manually-create-a-service-principal)
- Install [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli?view=azure-cli-latest)
- Install Maven
- Install `envsubst` utility

### Create AKS cluster & Azure Container Registry

```bash
az login
az group create -l eastus -n <rersource-group-name>
az aks create -g <rersource-group-name> -n <cluster-name> --service-principal <service-principal-id> --client-secret <client-secret>
az aks get-credentials -g <rersource-group-name> -n <cluster-name> --overwrite-existing
az acr create -g <rersource-group-name> -n <registry-name> --sku Basic --admin-enabled
```

### Build images

```bash
./build.sh <key-store-password> <registry-name> clean
```

### Deploy and run containerized applications on AKS

```bash
./deploy.sh <cluster-name> <registry-server-name>
```

## Live demo

See live demo video from [this link](./media/PiggyMetrics_on_Open_Liberty.mp4).
