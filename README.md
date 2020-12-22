# Piggy Metrics on Open Liberty

## Description

It's a demo application for Open Liberty MicroProfile, which references the following major open-source projects:

- [`piggymetrics`](https://github.com/sqshq/piggymetrics): front-end, REST APIs definition and business logics 
- [`sample-acmegifts`](https://github.com/OpenLiberty/sample-acmegifts): architecture and OpenLiberty ways to implement micro-services
- [`jnosql-artemis`](https://github.com/eugenp/tutorials/tree/master/persistence-modules/jnosql/jnosql-artemis): use Eclipse JNoSQL to easily interact with MongoDB
- [`openliberty-config-example`](https://github.com/sdaschner/openliberty-config-example/tree/prometheus-k8s): use Prometheus to collect metrics data of micro-services and get them visualized/monitored in Grafana dashboard

Note: The notification service from the original [`piggymetrics`](https://github.com/sqshq/piggymetrics) project is not ported yet, due to the time limitation. This will be implemented when time permits in the future.

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
export RESOURCE_GROUP_NAME=<resource-group-name>
export CLUSTER_NAME=<cluster-name>
export SERVICE_PRINCIPAL_ID=<service-principal-id>
export CLIENT_SECRET=<client-secret>
export REGISTRY_NAME=<registry-name>
az login
az group create -l eastus -n $RESOURCE_GROUP_NAME
az aks create -g $RESOURCE_GROUP_NAME -n $CLUSTER_NAME --service-principal $SERVICE_PRINCIPAL_ID --client-secret $CLIENT_SECRET --generate-ssh-keys
az aks get-credentials -g $RESOURCE_GROUP_NAME -n $CLUSTER_NAME --overwrite-existing
az acr create -g $RESOURCE_GROUP_NAME -n $REGISTRY_NAME --sku Basic --admin-enabled
export REGISTRY_SERVER=$(az acr show -n $REGISTRY_NAME --query loginServer | tr -d '"')
```

### Generate images

```bash
export KEYSTORE_PASSWORD=<key-store-password>
./build.sh $KEYSTORE_PASSWORD $REGISTRY_SERVER $REGISTRY_NAME clean
```

### Deploy and run containerized applications on AKS

```bash
./deploy.sh $REGISTRY_SERVER
```

### Live demo

See live demo video from [this link](./media/PiggyMetrics_on_Open_Liberty.mp4) to understand how to visit different web consoles, including Swagger UI, PiggyMetrics web console, Zipkin web console, Grafana web console ([Dashboard 11706](https://grafana.com/grafana/dashboards/11706)) and Kibaba web console.

## Cleanup

### Delete Piggy Metrics related resources

```bash
kubectl delete namespace piggymetrics
```

### Delete ACR & AKS

```bash
az group delete -n $RESOURCE_GROUP_NAME
```
