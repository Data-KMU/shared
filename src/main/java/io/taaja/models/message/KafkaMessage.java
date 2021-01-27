package io.taaja.models.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.taaja.models.message.data.update.SpatialDataUpdate;
import io.taaja.models.message.extension.operation.SpatialEntityOperation;
import lombok.Data;


@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "messageChannel"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpatialEntityOperation.class, name = MessageChannel.SpatialOperation),
        @JsonSubTypes.Type(value = SpatialDataUpdate.class, name = MessageChannel.SpatialDataUpdate)
})
public abstract class KafkaMessage {

    private String publisherId;

}
