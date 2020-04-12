#!/bin/sh

clean=$1

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
