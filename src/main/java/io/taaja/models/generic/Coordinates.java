package io.taaja.models.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties("timestamp") /* dedrone position includes timestamp */
public class Coordinates {
    private float latitude;
    private float longitude;
    private Float altitude;
}