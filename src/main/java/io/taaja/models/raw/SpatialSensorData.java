package io.taaja.models.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.taaja.models.message.data.update.SpatialDataUpdate;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpatialSensorData extends SensorData {

    private List<String> intersectingSpatialEntities = new LinkedList<>();


    public static SpatialSensorData createSpatialSensorData(List<String> intersectingSpatialEntitiesIds, SensorData sensorData){
        SpatialSensorData spatialSensorData = new SpatialSensorData();
        spatialSensorData.setAccuracy(sensorData.getAccuracy());
        spatialSensorData.setCreated(sensorData.getCreated());
        spatialSensorData.setInformationType(sensorData.getInformationType());
        spatialSensorData.setPayload(sensorData.getPayload());
        spatialSensorData.setAltitude(sensorData.getAltitude());
        spatialSensorData.setLatitude(sensorData.getLatitude());
        spatialSensorData.setLongitude(sensorData.getLongitude());
        spatialSensorData.setIntersectingSpatialEntities(intersectingSpatialEntitiesIds);
        return spatialSensorData;
    }

}
