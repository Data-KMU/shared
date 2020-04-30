package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.*;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;
import org.mongojack.Id;

import java.util.Date;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "extensionType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Area.class, name = "Area"),
        @JsonSubTypes.Type(value = Corridor.class, name = "Corridor")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class SpatialEntity implements Comparable<SpatialEntity> {

    @Id
    @JsonProperty("_id")
    @JsonView(SpatialRecordView.Identity.class)
    private String id;

    @JsonView(SpatialRecordView.Identity.class)
    private int priority = ExtensionPriority.DEFAULT_PRIORITY;

    @JsonView(SpatialRecordView.Identity.class)
    private ExtensionBehaviour extensionBehaviour = ExtensionBehaviour.Default;

    @JsonView(SpatialRecordView.Identity.class)
    private String extensionType;

    @JsonView(SpatialRecordView.Identity.class)
    private Date created = new Date();

    @JsonView(SpatialRecordView.Full.class)
    private Date validFrom;

    @JsonView(SpatialRecordView.Full.class)
    private Date validUntil;

    @JsonView(SpatialRecordView.Full.class)
    private Object actuators;

    @JsonView(SpatialRecordView.Full.class)
    private Object sensors;

    @JsonView(SpatialRecordView.Full.class)
    private Object samplers;


    @Override
    public int compareTo(SpatialEntity spatialEntity) {
        if (spatialEntity == null)
            return 1;
        return this.getPriority() - spatialEntity.getPriority();
    }

}
