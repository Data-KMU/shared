package io.taaja.models.generic;

import com.fasterxml.jackson.annotation.JsonView;
import io.taaja.models.record.spatial.SpatialEntity;
import io.taaja.models.views.SpatialRecordView;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class LocationInformation {

    private List<SpatialEntity> spatialEntities;

    private float longitude;

    private float latitude;

    //nullable
    private Float altitude;

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
