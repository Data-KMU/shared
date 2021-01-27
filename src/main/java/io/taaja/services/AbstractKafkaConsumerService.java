package io.taaja.services;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.taaja.kafka.Topics;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.regex.Pattern;

@JBossLog
public abstract class AbstractKafkaConsumerService extends AbstractService {

    @ConfigProperty(name = "kafka.bootstrap-servers")
    protected String bootstrapServers;

    @ConfigProperty(name = "kafka.poll-records", defaultValue = "100")
    protected int pollRecords;

    @ConfigProperty(name = "kafka.auto-commit", defaultValue = "true")
    protected boolean autoCommit;

    @ConfigProperty(name = "kafka.offset-reset", defaultValue = "earliest")
    protected String offsetReset;

    @ConfigProperty(name = "kafka.group-name")
    protected String groupName;

    protected String groupId;

    protected String consumerId;

    private ExtensionLivDataConsumer livDataConsumer;

    private class ExtensionLivDataConsumer extends Thread implements Closeable {

        private volatile boolean running = true;
        private final Properties consumerProperties = new Properties();

        private ExtensionLivDataConsumer() {
            consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AbstractKafkaConsumerService.this.bootstrapServers);
            consumerProperties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, AbstractKafkaConsumerService.this.pollRecords);
            consumerProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, AbstractKafkaConsumerService.this.autoCommit);
            consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AbstractKafkaConsumerService.this.offsetReset);
            consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, AbstractKafkaConsumerService.this.groupId);
            consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, AbstractKafkaConsumerService.this.consumerId);
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void run() {
            KafkaConsumer kafkaConsumer = new KafkaConsumer(this.consumerProperties, new StringDeserializer(), new StringDeserializer());
//            kafkaConsumer.subscribe(Pattern.compile(Topics.SPATIAL_EXTENSION_LIFE_DATA_TOPIC_PREFIX + "*"));
            kafkaConsumer.subscribe(Pattern.compile("^" + Topics.SPATIAL_EXTENSION_LIFE_DATA_TOPIC_PREFIX + ".*"));
            log.info("Subscribed topics: " + String.join(", ", kafkaConsumer.listTopics().keySet()) );
            while (this.running){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, String> record : records){
                    AbstractKafkaConsumerService.this.processRecord(record);
                }
            }
            kafkaConsumer.close();
        }

        @Override
        public void close() throws IOException {
            this.running = false;
        }
    }

    protected abstract void processRecord(ConsumerRecord<String, String> record);

    protected abstract String getGroupId(String clientId, String groupName);

    public void onStart(@Observes StartupEvent ev) {
        this.consumerId = this.groupName + "#" + UUID.randomUUID().toString();
        this.groupId = this.getGroupId(this.consumerId, this.groupName);

        log.info("starting kafka consumer. groupId: " + this.groupId +" consumer id: " + this.consumerId);
        this.livDataConsumer = new ExtensionLivDataConsumer();
        this.livDataConsumer.start();
    }

    public void onStop(@Observes ShutdownEvent ev) throws IOException {
        log.info("shutting down kafka consumer. groupId: " + this.groupId +" consumer id: " + this.consumerId);
        this.livDataConsumer.close();
    }

}
