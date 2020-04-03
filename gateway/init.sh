#!/bin/bash

auth_host=${AUTH_HOST:-localhost}
auth_port=${AUTH_PORT:-9180}
account_host=${ACCOUNT_HOST:-localhost}
account_port=${ACCOUNT_PORT:-9280}

pwd=$1
warPkg=$2

cd "$pwd"

jar -xf "$warPkg"
sed -i "s#authServiceClient/mp-rest/uri=.*#authServiceClient/mp-rest/uri=http://${auth_host}:${auth_port}#g" WEB-INF/classes/META-INF/microprofile-config.properties
sed -i "s#accountServiceClient/mp-rest/uri=.*#accountServiceClient/mp-rest/uri=http://${account_host}:${account_port}#g" WEB-INF/classes/META-INF/microprofile-config.properties
jar -uf "$warPkg" WEB-INF/classes/META-INF/microprofile-config.properties
rm -rf css/ fonts/ images/ js/ META-INF/ WEB-INF/ attribution.html index.html

/opt/ol/wlp/bin/server run defaultServer