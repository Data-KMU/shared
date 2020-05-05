package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "longitude", "latitude", "altitude", "additionalData" })
public class Waypoint extends LongLat {

    Float altitude;
    Object additionalData;

    public Waypoint(){}

    public Waypoint(float longitude, float latitude){
        super(longitude, latitude);
    }

    public Waypoint(float longitude, float latitude, Float altitude, Object additionalData){
        this(longitude, latitude);
        this.altitude = altitude;
        this.additionalData = additionalData;
    }

}
