package io.taaja.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.common.serialization.Serializer;

@JBossLog
public class JacksonSerializer implements Serializer {

    private static ObjectMapper objectMapperInstance;

    @Override
    public byte[] serialize(String topic, Object data) {
        byte[] retVal = null;
        if(objectMapperInstance == null){
            objectMapperInstance = new ObjectMapper();
        }
        try {
            retVal =  objectMapperInstance.writeValueAsBytes(data);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return retVal;
    }
}
