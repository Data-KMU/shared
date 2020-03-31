package io.taaja.models.kafka.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.taaja.models.kafka.update.actuator.AbstractActuatorUpdate;
import io.taaja.models.kafka.update.sampler.AbstractSamplerUpdate;
import io.taaja.models.kafka.update.sensor.AbstractSensorUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * used to create an partial update
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PartialUpdate {

    // entity id -> Data
    private Map<String, AbstractActuatorUpdate> actuators = new HashMap<>();
    private Map<String, AbstractSensorUpdate> sensors = new HashMap<>();
    private Map<String, AbstractSamplerUpdate> samplers = new HashMap<>();

    @SneakyThrows
    @Override
    public String toString(){
        return new ObjectMapper().writeValueAsString(this);
    }

    public PartialUpdate(){}

    public PartialUpdate(String id, AbstractActuatorUpdate abstractActuatorUpdate){
        this.getActuators().put(id, abstractActuatorUpdate);
    }

    public PartialUpdate(String id, AbstractSensorUpdate abstractSensorUpdate){
        this.getSensors().put(id, abstractSensorUpdate);
    }

    public PartialUpdate(String id, AbstractSamplerUpdate abstractSamplerUpdate){
        this.getSamplers().put(id, abstractSamplerUpdate);
    }

}
