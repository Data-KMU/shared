package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExtensionBehaviour {

    Default("default"),
    ExclusionZone("exclusionZone");

    private final String value;

    ExtensionBehaviour(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
