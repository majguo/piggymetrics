#!/bin/sh

clusterContext=$1
acrServer=$2
elasticCloudId=$3
elasticCloudUsername=$4
elasticCloudPassword=$5

export AUTH_IMAGE=${acrServer}/auth-service:1.0
export STATISTICS_IMAGE=${acrServer}/statistics-service:1.0
export ACCOUNT_IMAGE=${acrServer}/account-service:1.0
export GATEWAY_IMAGE=${acrServer}/gateway:1.0
export NAMESPACE=piggymetrics

kubectl config use-context ${clusterContext}
kubectl create namespace ${NAMESPACE}

envsubst < deployment/prometheus-rbac.yaml | kubectl apply --namespace=${NAMESPACE} -f -
kubectl apply --namespace=${NAMESPACE} -f deployment/prometheus.yaml
kubectl apply --namespace=${NAMESPACE} -f deployment/grafana.yaml
kubectl apply --namespace=${NAMESPACE} -f deployment/zipkin.yaml
kubectl apply --namespace=${NAMESPACE} -f deployment/mongo.yaml

if [ ! -z "$elasticCloudId" ] && [ ! -z "$elasticCloudUsername" ] && [ ! -z "$elasticCloudPassword" ]; then
    export ELASTIC_CLOUD_ID=${elasticCloudId}
    export ELASTIC_CLOUD_AUTH=${elasticCloudUsername}:${elasticCloudPassword}
    envsubst < deployment/filebeat-elastic-hosted.yaml | kubectl apply --namespace=${NAMESPACE} -f -
else
    kubectl apply -f https://download.elastic.co/downloads/eck/1.0.1/all-in-one.yaml
    export ELASTIC_NAME=quickstart
    envsubst < deployment/elasticsearch.yaml | kubectl apply --namespace=${NAMESPACE} -f -
    envsubst < deployment/kibana.yaml | kubectl apply --namespace=${NAMESPACE} -f -
    export ELASTICSEARCH_PASSWORD=$(kubectl get secret --namespace=${NAMESPACE} ${ELASTIC_NAME}-es-elastic-user -o=jsonpath='{.data.elastic}' | base64 --decode)
    export ELASTICSEARCH_USERNAME=elastic
    export ELASTICSEARCH_HOST=${ELASTIC_NAME}-es-http
    export ELASTICSEARCH_PORT=9200
    envsubst < deployment/filebeat-elastic-on-k8s.yaml | kubectl apply --namespace=${NAMESPACE} -f -
fi

envsubst < deployment/piggymetrics.yaml | kubectl apply --namespace=${NAMESPACE} -f -

if [ ! -z "$ELASTICSEARCH_PASSWORD" ]; then
    echo "ELASTICSEARCH_PASSWORD: ${ELASTICSEARCH_PASSWORD}"
fi
