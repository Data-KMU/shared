package io.taaja.models.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpatialSensorData extends SensorData {

    private List<String> intersectingSpatialEntities = new LinkedList<>();

}
