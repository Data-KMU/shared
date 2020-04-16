package io.taaja.models.spatial.operation;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpatialOperation {

    private String targetId;
    private OperationType operationType;

    private List<String> intersectingExtensions;
    private Date timestamp = new Date();

}
