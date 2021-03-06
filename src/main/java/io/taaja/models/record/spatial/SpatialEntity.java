package io.taaja.models.record.spatial;

import com.fasterxml.jackson.annotation.*;
import io.taaja.models.views.SpatialRecordView;
import jdk.jfr.Unsigned;
import lombok.Data;
import org.mongojack.Id;

import java.util.Date;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        //geo Json attribute type
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Area.class, name = Area.TYPE_VALUE),
        @JsonSubTypes.Type(value = Corridor.class, name = Corridor.TYPE_VALUE)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class SpatialEntity implements Comparable<SpatialEntity> {

    @Id
    @JsonProperty("_id")
    @JsonView(SpatialRecordView.Identity.class)
    private String id;

    @Unsigned
    @JsonView(SpatialRecordView.Identity.class)
    private int priority = ExtensionPriority.DEFAULT_PRIORITY;

    @JsonView(SpatialRecordView.Identity.class)
    private ExtensionBehaviour extensionBehaviour = ExtensionBehaviour.TrafficZone;

    @JsonView(SpatialRecordView.Identity.class)
    private Date created = new Date();

    @JsonView(SpatialRecordView.Identity.class)
    private Date validFrom;

    @JsonView(SpatialRecordView.Identity.class)
    private Date validUntil;

    @JsonView(SpatialRecordView.Full.class)
    private Object actuators;

    @JsonView(SpatialRecordView.Full.class)
    private Object sensors;

    @JsonView(SpatialRecordView.Full.class)
    private Object samplers;

    @JsonView(SpatialRecordView.Full.class)
    private Object properties;

    @Override
    public int compareTo(SpatialEntity spatialEntity) {
        if (spatialEntity == null)
            return 1;
        return this.calculateTotalPriority() - spatialEntity.calculateTotalPriority();
    }

    public int calculateTotalPriority(){
        if(this.getPriority() > ExtensionPriority.MAX_PRIORITY){
            this.setPriority(ExtensionPriority.MAX_PRIORITY);
        }
        if(this.getPriority() < 0){
            this.setPriority(0);
        }

        return (this.getExtensionBehaviour().getValue() << Short.SIZE) | this.getPriority();
    }

}
