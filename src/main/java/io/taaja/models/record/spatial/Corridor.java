package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;

import java.util.List;

@Data
public class Corridor extends SpatialEntity {

    public static final String TYPE_VALUE = "Corridor";

    public enum ShapeType{
        Circular("circular");

        private final String value;

        ShapeType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    @JsonView(SpatialRecordView.Coordinates.class)
    private ShapeType shape;

    @JsonView(SpatialRecordView.Coordinates.class)
    private List<Waypoint> coordinates;

}
