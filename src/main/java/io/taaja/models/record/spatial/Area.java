package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.OptBoolean;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;

import java.util.List;

@Data
public class Area extends SpatialEntity {

    public static final String TYPE_VALUE = "Area";

    @JsonView(SpatialRecordView.Coordinates.class)
    private Float elevation;

    @JsonView(SpatialRecordView.Coordinates.class)
    private Float height;

    @JsonView(SpatialRecordView.Coordinates.class)
    private List<List<LongLat>> coordinates;

}
