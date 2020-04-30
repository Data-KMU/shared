package io.taaja.models.message.data.update;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.taaja.models.message.data.update.actuator.AbstractActuatorUpdate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericSpatialUpdate extends AbstractActuatorUpdate {

    //used to provide a fallback..

}
