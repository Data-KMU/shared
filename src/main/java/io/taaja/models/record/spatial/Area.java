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

    @JsonView(SpatialRecordView.Full.class)
    private Float elevation;

    @JsonView(SpatialRecordView.Full.class)
    private Float height;

    @JsonMerge(value = OptBoolean.FALSE)
    @JsonView(SpatialRecordView.Full.class)
    private List<List<LongLat>> coordinates;

}
