package io.taaja.models.spatial;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExtensionType {
    Corridor("Corridor"),
    Area("Area");

    private final String value;

    ExtensionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
