package io.taaja.models.message.data.update;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.Date;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = GenericSpatialUpdate.class
)
public abstract class AbstractUpdatePart {

    private Date modified = new Date();

}
