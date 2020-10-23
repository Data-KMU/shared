package io.taaja.models.message.data.update.impl;

import io.taaja.models.generic.Coordinates;
import io.taaja.models.message.data.update.SpatialDataUpdate;
import lombok.Data;

@Data
public class PositionUpdate {

    private Coordinates position;

    public static SpatialDataUpdate createPositionUpdate(String entityId, Coordinates coordinates){
        SpatialDataUpdate spatialDataUpdate = new SpatialDataUpdate();
        spatialDataUpdate.getActuators().put(entityId, coordinates);
        return spatialDataUpdate;
    }


}
