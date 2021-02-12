package io.taaja.models.generic;

import lombok.Data;

@Data
public class Coordinates {

    private float longitude;
    private float latitude;
    private Float altitude;
    private Float elevation;

    public Coordinates(){}

    public Coordinates(float longitude, float latitude){
        this(longitude, latitude, null, null);
    }

    public Coordinates(float longitude, float latitude,  Float altitude){
        this(longitude, latitude, altitude, null);
    }

    public Coordinates(float longitude, float latitude,  Float altitude, Float elevation){
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.elevation = elevation;
    }

}