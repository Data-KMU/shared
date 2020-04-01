package io.taaja.models.spatial.data.update.sampler;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.taaja.models.spatial.data.update.AbstractUpdate;
import lombok.Data;


@Data
@JsonSubTypes({

})
public abstract class AbstractSamplerUpdate extends AbstractUpdate {

}
