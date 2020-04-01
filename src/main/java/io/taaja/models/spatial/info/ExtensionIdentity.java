package io.taaja.models.spatial.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taaja.models.ExtensionPriority;
import io.taaja.models.spatial.ExtensionType;
import lombok.Data;
import org.mongojack.Id;

@Data
public class ExtensionIdentity implements Comparable<ExtensionIdentity>{

    @Id
    @JsonProperty("_id")
    private String id;

    private int priority = ExtensionPriority.DEFAULT_PRIORITY;
    private ExtensionType type = ExtensionType.Default;

    @Override
    public int compareTo(ExtensionIdentity extensionIdentity) {
        if(extensionIdentity == null)
            return 1;
        return this.getPriority() - extensionIdentity.getPriority();
    }

}
