package io.taaja.models.kafka.update.sensor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.kafka.update.AbstractUpdate;
import lombok.Data;


@Data
@JsonSubTypes({

})
public abstract class AbstractSensorUpdate extends AbstractUpdate {

}
