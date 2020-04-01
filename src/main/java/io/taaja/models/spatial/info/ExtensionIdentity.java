package io.taaja.models.spatial.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.taaja.models.ExtensionPriority;
import io.taaja.models.spatial.ExtensionBehaviour;
import io.taaja.models.spatial.ExtensionType;
import lombok.Data;
import org.mongojack.Id;

import java.util.Date;

@Data
public class ExtensionIdentity implements Comparable<ExtensionIdentity>{

    @Id
    @JsonProperty("_id")
    private String id;

    private int priority = ExtensionPriority.DEFAULT_PRIORITY;
    private ExtensionBehaviour extensionBehaviour = ExtensionBehaviour.Default;
    private ExtensionType type;

    private Date created = new Date();
    private Date validFrom;
    private Date validUntil;

    @Override
    public int compareTo(ExtensionIdentity extensionIdentity) {
        if(extensionIdentity == null)
            return 1;
        return this.getPriority() - extensionIdentity.getPriority();
    }

}
