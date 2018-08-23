ENV=$1
if [ $ENV == '' ]
then
$ENV = "dev";
fi
java -Xms4G -Xmx4G -server -Xloggc:../logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=../logs -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9888 -Dcom.sun.management.jmxremote.ssl=FALSE -Dcom.sun.management.jmxremote.authenticate=FALSE -Dlogging.config=../config/log4j2.xml -Dspring.config.location=../config/application.properties,../config/messages_en.properties -jar ../lib/mace-backend-0.1.1-SNAPSHOT.jar