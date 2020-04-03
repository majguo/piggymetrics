#!/bin/bash

auth_host=${AUTH_HOST:-localhost}
auth_port=${AUTH_PORT:-9180}
account_host=${ACCOUNT_HOST:-localhost}
account_port=${ACCOUNT_PORT:-9280}

pwd=$1
warPkg=$2

cd "$pwd"

jar -xf "$warPkg"
sed -i "s#var AUTH_SVC_URL = .*#var AUTH_SVC_URL = \"http://${auth_host}:${auth_port}\"#g" js/config.js
sed -i "s#var ACCOUNT_SVC_URL = .*#var ACCOUNT_SVC_URL = \"http://${account_host}:${account_port}\"#g" js/config.js
jar -uf "$warPkg" js/config.js
rm -rf css/ fonts/ images/ js/ META-INF/ WEB-INF/ attribution.html index.html

/opt/ol/wlp/bin/server run defaultServer