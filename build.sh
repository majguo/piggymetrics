#!/bin/sh

keyStorePass=$1
registryServer=$2
registryName=$3
clean=$4

if [ "$clean" = clean ]; then
    mvn clean install package -DkeyStorePass=${keyStorePass}
else
    mvn install package -DkeyStorePass=${keyStorePass}
fi

docker rmi gateway:1.0
docker rmi auth-service:1.0
docker rmi account-service:1.0
docker rmi statistics-service:1.0

docker build -t gateway:1.0 gateway/
docker build -t auth-service:1.0 auth-service/
docker build -t account-service:1.0 account-service/
docker build -t statistics-service:1.0 statistics-service/

docker rmi ${registryServer}/gateway:1.0
docker rmi ${registryServer}/auth-service:1.0
docker rmi ${registryServer}/account-service:1.0
docker rmi ${registryServer}/statistics-service:1.0

docker tag gateway:1.0 ${registryServer}/gateway:1.0
docker tag auth-service:1.0 ${registryServer}/auth-service:1.0
docker tag account-service:1.0 ${registryServer}/account-service:1.0
docker tag statistics-service:1.0 ${registryServer}/statistics-service:1.0

az acr show -n ${registryName}
if [ $? -eq 0 ]; then
    # Log into Azure Container Registry
    az acr login -n ${registryName}
else
    # Log into Docker Hub without credential
    docker login
fi

docker push ${registryServer}/gateway:1.0
docker push ${registryServer}/auth-service:1.0
docker push ${registryServer}/account-service:1.0
docker push ${registryServer}/statistics-service:1.0
