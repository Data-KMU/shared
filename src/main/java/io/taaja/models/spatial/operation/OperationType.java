package io.taaja.models.spatial.operation;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationType {

    Created("created"),
    Altered("altered"),
    Removed("removed");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
