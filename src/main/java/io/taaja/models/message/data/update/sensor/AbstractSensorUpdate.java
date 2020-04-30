package io.taaja.models.message.data.update.sensor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.message.data.update.AbstractUpdatePart;
import lombok.Data;


@Data
@JsonSubTypes({

})
public abstract class AbstractSensorUpdate extends AbstractUpdatePart {

}
