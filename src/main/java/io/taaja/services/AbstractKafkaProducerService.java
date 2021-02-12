package io.taaja.services;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.taaja.kafka.JacksonSerializer;
import io.taaja.kafka.Topics;
import io.taaja.models.message.KafkaMessage;
import io.taaja.models.record.spatial.SpatialEntity;
import lombok.Getter;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * Deprecated: use smallrye
 */
@JBossLog
@Getter
@Deprecated
public abstract class AbstractKafkaProducerService extends AbstractService {

    @ConfigProperty(name = "kafka.bootstrap-servers")
    protected String bootstrapServers;

    protected Producer<String, KafkaMessage> kafkaProducer;

    protected String originId;


    public void onStart(@Observes StartupEvent ev) {
        this.originId = this.getOriginID();
        log.info("starting kafka producer with id: " + this.originId);
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, this.originId);
        this.kafkaProducer = new KafkaProducer(producerProperties, new StringSerializer(), new JacksonSerializer());
    }

    public void onStop(@Observes ShutdownEvent ev) throws IOException {
        log.info("shutdown kafka producer with id: " + this.originId);
        this.kafkaProducer.close();
    }

    public void publish(final KafkaMessage kafkaMessage, final Iterable<SpatialEntity> spatialEntities){
        kafkaMessage.setPublisherId(this.originId);
        spatialEntities.forEach(spatialEntity -> this.publish(kafkaMessage, spatialEntity.getId()));
    }

    public void publish(final KafkaMessage kafkaMessage, final String spatialEntityId){
        kafkaMessage.setPublisherId(this.originId);
        this.sendInter(kafkaMessage, spatialEntityId);
    }

    private void sendInter(final KafkaMessage kafkaMessage, final String spatialEntityId){
        Future<RecordMetadata> send = AbstractKafkaProducerService.this.kafkaProducer.send(
                new ProducerRecord<>(
                        Topics.SPATIAL_EXTENSION_LIFE_DATA_TOPIC_PREFIX + spatialEntityId,
                        this.originId,
                        kafkaMessage
                )
        );
    }

    protected String getOriginID(){
        return UUID.randomUUID().toString();
    }


}
