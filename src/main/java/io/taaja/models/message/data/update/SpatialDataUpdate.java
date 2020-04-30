package io.taaja.models.message.data.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.taaja.models.message.KafkaMessage;
import io.taaja.models.message.data.update.sensor.AbstractSensorUpdate;
import io.taaja.models.message.data.update.actuator.AbstractActuatorUpdate;
import io.taaja.models.message.data.update.sampler.AbstractSamplerUpdate;
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
    private Map<String, AbstractActuatorUpdate> actuators = new HashMap<>();
    private Map<String, AbstractSensorUpdate> sensors = new HashMap<>();
    private Map<String, AbstractSamplerUpdate> samplers = new HashMap<>();

    @SneakyThrows
    @Override
    public String toString(){
        return (new ObjectMapper()).writeValueAsString(this);
    }

    public SpatialDataUpdate(){}

    public SpatialDataUpdate(String id, AbstractActuatorUpdate abstractActuatorUpdate){
        this.getActuators().put(id, abstractActuatorUpdate);
    }

    public SpatialDataUpdate(String id, AbstractSensorUpdate abstractSensorUpdate){
        this.getSensors().put(id, abstractSensorUpdate);
    }

    public SpatialDataUpdate(String id, AbstractSamplerUpdate abstractSamplerUpdate){
        this.getSamplers().put(id, abstractSamplerUpdate);
    }

}
