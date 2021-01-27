package io.taaja.models.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.taaja.models.generic.Coordinates;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData extends Coordinates {

    private InformationType informationType;

    private Date created = new Date();

    private float accuracy;

    private Object payload;

}
