package io.taaja.models.zoning;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import org.mongojack.Id;
import org.mongojack.ObjectId;

@Data
public class Extension {

    public enum ExtensionType {
        Area("Area"),
        Corridor("Corridor");

        private final String value;

        ExtensionType(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    @Id
    private String id;
    private ExtensionType type;

}
