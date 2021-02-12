package io.taaja.models.raw;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.taaja.models.generic.Coordinates;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData extends Coordinates {

    /**
     * may describe the originator of this information
     * This is used to differentiate between sensors and vehicle.
     * Can ba empty oder UUID
     */
    private String originId;

    /**
     * determins the type of inofmation
     * vehicleInformation = describes the position and data of a vehicle
     * sensorInformation = describes the value of an observation (e.g. temperature)
     * metaInformation = describes Information about the sensor network (e.g. sensor offline)
     */
    private InformationType informationType;

    /**
     * creation date of measurement / event
     */
    private Date created = new Date();

    /**
     * Accuracy of Coordinates in meters. Eg:
     * 10m deviation (+/- 10m)
     * 0 --> exact Position
     * -1 deviation unknown
     */
    private float deviation;

    /**
     * Additional arbitrary data
     */
    private Object payload;

}
