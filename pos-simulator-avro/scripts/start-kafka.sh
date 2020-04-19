sh $CONFLUENT_KAFKA_HOME/bin/zookeeper-server-start $CONFLUENT_KAFKA_HOME/etc/kafka/zookeeper.properties &
sh $CONFLUENT_KAFKA_HOME/bin/kafka-server-start $CONFLUENT_KAFKA_HOME/etc/kafka/server-0.properties &
sh $CONFLUENT_KAFKA_HOME/bin/kafka-server-start $CONFLUENT_KAFKA_HOME/etc/kafka/server-1.properties &
sh $CONFLUENT_KAFKA_HOME/bin/kafka-server-start $CONFLUENT_KAFKA_HOME/etc/kafka/server-2.properties &
sh $CONFLUENT_KAFKA_HOME/bin/schema-registry-start $CONFLUENT_KAFKA_HOME/etc/schema-registry/schema-registry.properties &