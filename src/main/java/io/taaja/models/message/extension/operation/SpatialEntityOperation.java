package io.taaja.models.message.extension.operation;

import io.taaja.models.message.KafkaMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpatialEntityOperation extends KafkaMessage {

    private String targetId;
    private OperationType operationType;

    private List<String> intersectingExtensions;
    private Date timestamp = new Date();

}
