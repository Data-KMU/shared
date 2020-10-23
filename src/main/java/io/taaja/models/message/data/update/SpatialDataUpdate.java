package io.taaja.models.message.data.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.taaja.models.message.KafkaMessage;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * used to create an partial update
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpatialDataUpdate extends KafkaMessage {

    // entity id -> Data
    private Map<String, Object> actuators = new HashMap<>();
    private Map<String, Object> sensors = new HashMap<>();
    private Map<String, Object> samplers = new HashMap<>();

    @SneakyThrows
    @Override
    public String toString() {
        return (new ObjectMapper()).writeValueAsString(this);
    }

}