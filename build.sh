#!/bin/sh

acrName=$1
acrServer=${acrName}.azurecr.io
clean=$2

if [ "$clean" = clean ]; then
    mvn clean install package
else
    mvn install package
fi

docker rmi gateway:1.0
docker rmi auth-service:1.0
docker rmi account-service:1.0
docker rmi statistics-service:1.0

docker build -t gateway:1.0 gateway/
docker build -t auth-service:1.0 auth-service/
docker build -t account-service:1.0 account-service/
docker build -t statistics-service:1.0 statistics-service/

docker rmi ${acrServer}/gateway:1.0
docker rmi ${acrServer}/auth-service:1.0
docker rmi ${acrServer}/account-service:1.0
docker rmi ${acrServer}/statistics-service:1.0

docker tag gateway:1.0 ${acrServer}/gateway:1.0
docker tag auth-service:1.0 ${acrServer}/auth-service:1.0
docker tag account-service:1.0 ${acrServer}/account-service:1.0
docker tag statistics-service:1.0 ${acrServer}/statistics-service:1.0

az acr login -n ${acrName}
docker push ${acrServer}/gateway:1.0
docker push ${acrServer}/auth-service:1.0
docker push ${acrServer}/account-service:1.0
docker push ${acrServer}/statistics-service:1.0
