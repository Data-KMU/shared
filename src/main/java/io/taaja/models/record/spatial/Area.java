package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.JsonView;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;

import java.util.List;

@Data
public class Area extends SpatialEntity {

    @JsonView(SpatialRecordView.Full.class)
    private Float elevation;

    @JsonView(SpatialRecordView.Full.class)
    private Float height;

    @JsonView(SpatialRecordView.Full.class)
    private List<List<LongLat>> coordinates;

}
