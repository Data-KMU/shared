package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;

import java.util.List;

@Data
public class Corridor extends SpatialEntity {

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

    @JsonView(SpatialRecordView.Full.class)
    private ShapeType shape;

    @JsonView(SpatialRecordView.Full.class)
    private List<List<Waypoint>> coordinates;

}
