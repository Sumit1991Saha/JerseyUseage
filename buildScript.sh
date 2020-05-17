# chmod 777 TOMCAT_INSTALLATION_DIR/bin/catalina.sh

echo "Stopping Tomcat"
/Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35/bin/catalina.sh stop

echo "\n Building Project"
mvn clean install

echo "\n Moving Project War file"
rm -rf /Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35/webapps/JerseyUseage
rm /Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35/webapps/JerseyUseage.war
cp /Users/sumit.saha/codebase/JerseyUseage/target/JerseyUseage.war /Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35/webapps

echo "\n Starting Tomcat"
/Users/sumit.saha/Desktop/ApacheTools/apache-tomcat-9.0.35/bin/catalina.sh start
