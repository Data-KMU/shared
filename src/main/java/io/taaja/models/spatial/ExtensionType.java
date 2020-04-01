package io.taaja.models.spatial;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExtensionType {

    Default("default"),
    ExclusionZone("exclusionZone");

    private final String value;

    ExtensionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
