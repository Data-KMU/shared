package io.taaja.models.spatial.data.update.actuator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.spatial.data.update.AbstractUpdate;
import lombok.Data;


@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = PositionUpdate.class, name = "PositionUpdate")
})
public abstract class AbstractActuatorUpdate extends AbstractUpdate {


}
