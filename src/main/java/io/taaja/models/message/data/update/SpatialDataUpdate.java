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
    private Map<String, Object> vehicleInformation = new HashMap<>();
    private Map<String, Object> sensorInformation = new HashMap<>();
    private Object metaInformation;

    public SpatialDataUpdate addVehicleInformation(String vehicleId, Object data){
        this.getVehicleInformation().put(vehicleId, data);
        return this;
    }

    public SpatialDataUpdate addSensorInformation(String sensorId, Object data){
        this.getSensorInformation().put(sensorId, data);
        return this;
    }


    @SneakyThrows
    @Override
    public String toString() {
        return (new ObjectMapper()).writeValueAsString(this);
    }

}