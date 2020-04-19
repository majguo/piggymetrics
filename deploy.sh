#!/bin/sh

clusterContext=$1
acrName=$2
acrServer=${acrName}.azurecr.io
elasticCloudId=$3
elasticCloudUsername=$4
elasticCloudPassword=$5

export AUTH_IMAGE=${acrServer}/auth-service:1.0
export STATISTICS_IMAGE=${acrServer}/statistics-service:1.0
export ACCOUNT_IMAGE=${acrServer}/account-service:1.0
export GATEWAY_IMAGE=${acrServer}/gateway:1.0
export ELASTIC_CLOUD_ID=${elasticCloudId}
export ELASTIC_CLOUD_AUTH=${elasticCloudUsername}:${elasticCloudPassword}
export NAMESPACE=piggymetrics

kubectl config use-context ${clusterContext}
kubectl create namespace ${NAMESPACE}

envsubst < deployment/prometheus-rbac.yaml | kubectl apply --namespace=${NAMESPACE} -f -
kubectl apply --namespace=${NAMESPACE} -f deployment/prometheus.yaml
kubectl apply --namespace=${NAMESPACE} -f deployment/grafana.yaml

envsubst < deployment/piggymetrics.yaml | kubectl apply --namespace=${NAMESPACE} -f -
