package io.taaja.models.spatial.info;

import lombok.Data;

import java.util.*;

import static java.util.Collections.sort;
import static java.util.Comparator.naturalOrder;

@Data
public class LocationInformation {

    private List<Extension> extensions;

    private float longitude;
    private float latitude;
    //nullable
    private Float altitude;

    private Date created;

    public LocationInformation(){
        this.created = new Date();
        this.extensions = new ArrayList<>();
    }

    public List<Extension> getExtensions(){
        if(this.extensions != null){
            Collections.sort(this.extensions);
        }
        return this.extensions;
    }

}
