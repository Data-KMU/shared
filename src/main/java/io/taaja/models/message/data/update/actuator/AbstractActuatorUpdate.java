package io.taaja.models.message.data.update.actuator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.message.data.update.AbstractUpdatePart;
import lombok.Data;


@Data
@JsonSubTypes({
        @JsonSubTypes.Type(value = PositionUpdate.class, name = "PositionUpdate")
})
public abstract class AbstractActuatorUpdate extends AbstractUpdatePart {


}
