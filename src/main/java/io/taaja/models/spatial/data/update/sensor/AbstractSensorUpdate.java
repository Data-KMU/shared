package io.taaja.models.spatial.data.update.sensor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.spatial.data.update.AbstractUpdate;
import lombok.Data;


@Data
@JsonSubTypes({

})
public abstract class AbstractSensorUpdate extends AbstractUpdate {

}
