package io.taaja.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.quarkus.runtime.StartupEvent;
import io.taaja.models.generic.LocationInformation;
import io.taaja.models.record.spatial.SpatialEntity;
import io.taaja.models.views.SpatialRecordView;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.QueryParam;
import java.net.URI;


public abstract class AbstractIntersectingExtensionsService extends AbstractService{

    @ConfigProperty(name = "service.purple-tiger.url", defaultValue = "https://purpletiger.taaja.io")
    protected String url;

    @ConfigProperty(name = "service.purple-tiger.timeout", defaultValue="-1")
    protected int timeout;

    public static final String VERSION = "v1";

    protected ObjectWriter objectWriter;

    protected HttpClient client;
    protected RequestConfig requestConfig;
    protected ObjectReader objectReader;

    public void onStart(@Observes StartupEvent ev) {
        this.objectWriter = new ObjectMapper().writerWithView(SpatialRecordView.Coordinates.class);
        this.objectReader = new ObjectMapper().readerFor(LocationInformation.class);
        this.client = HttpClientBuilder.create().build();

        // 3 sec timeout
        if(timeout > -1){
            this.requestConfig = RequestConfig.custom().setConnectTimeout(this.timeout).build();
        }
    }

    @SneakyThrows
    public LocationInformation calculate(SpatialEntity spatialEntity){

        HttpPost httpPost = new HttpPost(this.url + "/" + AbstractIntersectingExtensionsService.VERSION + "/calculate/intersectingExtensions");

        httpPost.setEntity(new ByteArrayEntity(
                objectWriter.writeValueAsBytes(spatialEntity)
        ));
        httpPost.setHeader("Content-type", "application/json");
        if(this.requestConfig != null){
            httpPost.setConfig(this.requestConfig);
        }
        HttpResponse response = this.client.execute(httpPost);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new BadRequestException("cant resolve coordinates");
        }

        LocationInformation locationInformation = objectReader.readValue(
                response.getEntity().getContent()
        );
        locationInformation.setOriginator(spatialEntity);

        return locationInformation;
    }

    @SneakyThrows
    public LocationInformation calculate(
            @QueryParam("longitude") float longitude,
            @QueryParam("latitude") float latitude,
            @QueryParam("altitude") Float altitude
    ){

        HttpGet httpGet = new HttpGet(
                this.url + "/" + AbstractIntersectingExtensionsService.VERSION +
                        "/encode/position?longitude=" + longitude + "&latitude=" + latitude + (altitude == null ? "" : "&altitude=" + altitude));


        if(this.requestConfig != null){
            httpGet.setConfig(this.requestConfig);
        }
        HttpResponse response = this.client.execute(httpGet);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new BadRequestException("cant resolve coordinates");
        }

        return objectReader.readValue(
                response.getEntity().getContent()
        );
    }

}
