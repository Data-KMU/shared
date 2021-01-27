package io.taaja.models.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.taaja.models.record.spatial.SpatialEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Holds a list of SpatialEntities that are Intersecting with a point or another SpatialEntity
 */
@Data
public class LocationInformation extends Coordinates {

    @JsonIgnore
    private SpatialEntity originator;

    private List<SpatialEntity> spatialEntities;
    private Date created;

    public LocationInformation(){
        this.created = new Date();
        this.spatialEntities = new ArrayList<>();
    }


    public void addSpatialEntity(SpatialEntity spatialEntity){
        this.spatialEntities.add(spatialEntity);
    }

    public List<SpatialEntity> getSpatialEntities(){
        if(this.spatialEntities != null){
            Collections.sort(this.spatialEntities);
        }
        return this.spatialEntities;
    }

}
