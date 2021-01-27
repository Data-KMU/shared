package io.taaja.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.common.serialization.Serializer;

@JBossLog
public class JacksonSerializer implements Serializer {

    private static ObjectMapper objectMapperInstance = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Object data) {
        try {
            return objectMapperInstance.writeValueAsBytes(data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
