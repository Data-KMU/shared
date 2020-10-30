package io.taaja.models.message.data.update.impl;

import io.taaja.models.generic.Coordinates;
import io.taaja.models.message.data.update.SpatialDataUpdate;
import lombok.Data;

@Data
public class PositionUpdate {

    private Coordinates position;

    public static SpatialDataUpdate createPositionUpdate(String vehicleId, Coordinates coordinates){
        return new SpatialDataUpdate().addActuatorData(vehicleId, coordinates);
    }


}
