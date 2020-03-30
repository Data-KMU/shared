package io.taaja.models.zoning;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
