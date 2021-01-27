package io.taaja.models.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class Coordinates {

    private float longitude;
    private float latitude;
    private Float altitude;

    public Coordinates(){}

    public Coordinates(float longitude, float latitude,  Float altitude){
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Coordinates(float longitude, float latitude){
        this(longitude, latitude, null);
    }

}