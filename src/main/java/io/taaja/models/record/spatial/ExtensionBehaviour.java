package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExtensionBehaviour {

    DEFAULT("default", 8),
    TRAFFIC_ZONE("trafficZone", 128),
    EXCLUSION_ZONE("exclusionZone", 2048);

    /**
     * 0x0111111111111111
     */
    public static final int MAX_PRIORITY_VALUE = 32767;

    private final String name;
    private final int value;

    /**
     * max val: 32767
     */
    ExtensionBehaviour(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }



}
