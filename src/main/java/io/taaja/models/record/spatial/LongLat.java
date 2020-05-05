package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "longitude", "latitude" })
public class LongLat {

    private float longitude;
    private float latitude;

    public LongLat(){}

    public LongLat(float longitude, float latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
