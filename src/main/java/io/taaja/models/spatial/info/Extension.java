package io.taaja.models.spatial.info;

import io.taaja.models.spatial.ExtensionType;
import lombok.Data;

@Data
public class Extension  implements Comparable<Extension>{

    private String uuid;
    private int priority;
    private ExtensionType type;

    @Override
    public int compareTo(Extension extension) {
        if(extension == null)
            return 1;
        return this.getPriority() - extension.getPriority();
    }
}
