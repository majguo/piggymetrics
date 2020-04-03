#!/bin/sh

mvn clean install package

docker rmi gateway:1.0
docker rmi auth-service:1.0
docker rmi account-service:1.0
docker rmi statistics-service:1.0

docker build -t gateway:1.0 gateway/
docker build -t auth-service:1.0 auth-service/
docker build -t account-service:1.0 account-service/
docker build -t statistics-service:1.0 statistics-service/
