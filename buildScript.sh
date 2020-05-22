#!/bin/zsh

# chmod 777 TOMCAT_INSTALLATION_DIR/bin/catalina.sh

APACHE_TOMCAT_INSTALLATION_DIR="/Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35"
PROJECT_PATH="/Users/sumit.saha/codebase"
PROJECT_NAME="JerseyUseage"
WAR_PACKAGE=${PROJECT_NAME}.war

echo "Stopping Tomcat"
${APACHE_TOMCAT_INSTALLATION_DIR}/bin/catalina.sh stop

echo "\n Building Project"
mvn clean install

echo "\n Moving Project War file"
rm -rf ${APACHE_TOMCAT_INSTALLATION_DIR}/webapps/${PROJECT_NAME}
rm ${APACHE_TOMCAT_INSTALLATION_DIR}/webapps/${WAR_PACKAGE}
cp ${PROJECT_PATH}/${PROJECT_NAME}/target/${WAR_PACKAGE} ${APACHE_TOMCAT_INSTALLATION_DIR}/webapps
echo "\n War file moved"

case $1 in
  -d|-debug)
    echo "\n Starting Tomcat in debug mode"
    export JPDA_ADDRESS=8000
    export JPDA_TRANSPORT=dt_socket
    ${APACHE_TOMCAT_INSTALLATION_DIR}/bin/catalina.sh jpda run
    ;;
  *)
    echo "\n Starting Tomcat in standalone mode"
    ${APACHE_TOMCAT_INSTALLATION_DIR}/bin/catalina.sh start
    ;;
esac



