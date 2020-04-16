package io.taaja.models.spatial.info;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class LocationInformation {

    private List<ExtensionIdentity> extensions;

    private float longitude;
    private float latitude;
    //nullable
    private Float altitude;

    private Date created;

    public LocationInformation(){
        this.created = new Date();
        this.extensions = new ArrayList<>();
    }

    public List<ExtensionIdentity> getExtensions(){
        if(this.extensions != null){
            Collections.sort(this.extensions);
        }
        return this.extensions;
    }

}
