package io.taaja.models.spatial.data.update.actuator;

import io.taaja.models.generic.Coordinates;
import lombok.Data;

@Data
public class PositionUpdate extends AbstractActuatorUpdate {

    private Coordinates position;

    public PositionUpdate(){}

    public PositionUpdate(Coordinates position){
        this.position = position;
    }

}
