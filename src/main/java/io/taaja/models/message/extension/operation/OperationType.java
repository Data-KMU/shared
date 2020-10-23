package io.taaja.models.message.extension.operation;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationType {

    Created("created"),
    Altered("altered"),
    Removed("removed"),
    Unchanged("unchanged");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
