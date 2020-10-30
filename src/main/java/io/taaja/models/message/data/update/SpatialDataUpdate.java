package io.taaja.models.message.data.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpatialDataUpdate extends KafkaMessage {

    // entity id -> Data
    private Map<String, Object> actuators = new HashMap<>();
    private Map<String, Object> sensors = new HashMap<>();
    private Map<String, Object> samplers = new HashMap<>();

    public SpatialDataUpdate addActuatorData(String actuatorId, Object data){
        this.getActuators().put(actuatorId, data);
        return this;
    }

    public SpatialDataUpdate addSensorData(String sensorId, Object data){
        this.getSensors().put(sensorId, data);
        return this;
    }

    public SpatialDataUpdate addSamplerData(String samplerId, Object data){
        this.getSamplers().put(samplerId, data);
        return this;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return (new ObjectMapper()).writeValueAsString(this);
    }

}