package io.taaja.models.raw;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InformationType {

    VEHICLE_INFORMATION("vehicleInformation"),
    SENSOR_INFORMATION("sensorInformation"),
    META_INFORMATION("metaInformation");

    private final String value;

    InformationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }



}
