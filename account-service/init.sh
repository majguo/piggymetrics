#!/bin/bash

mongo_host=${MONGO_HOST:-localhost}
mongo_port=${MONGO_PORT:-27017}

pwd=$1
warPkg=$2

cd "$pwd"

jar -xf "$warPkg"
sed -i "s/\"mongodb.host\".*/\"mongodb.host\": \"${mongo_host}:${mongo_port}\"/g" WEB-INF/classes/META-INF/jnosql.json
jar -uf "$warPkg" WEB-INF/classes/META-INF/jnosql.json
rm -rf rm -rf META-INF/ WEB-INF/

/opt/ol/wlp/bin/server run defaultServer