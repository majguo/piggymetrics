#!/bin/bash

mongo_host=${MONGO_HOST:-localhost}
mongo_port=${MONGO_PORT:-27017}
zipkin_host=${ZIPKIN_HOST:-localhost}
zipkin_port=${ZIPKIN_PORT:-9411}

configPath=$1
warPkgPath=$2
warPkg=$3

cd "$configPath"
sed -i "s#opentracingZipkin host=.*#\opentracingZipkin host=\"${zipkin_host}\" port=\"${zipkin_port}\" />#g" server.xml

cd "$warPkgPath"

jar -xf "$warPkg"
sed -i "s/\"mongodb.host\".*/\"mongodb.host\": \"${mongo_host}:${mongo_port}\"/g" WEB-INF/classes/META-INF/jnosql.json
jar -uf "$warPkg" WEB-INF/classes/META-INF/jnosql.json
rm -rf META-INF/ WEB-INF/

/opt/ol/wlp/bin/server run defaultServer