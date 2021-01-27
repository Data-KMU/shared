package io.taaja.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.quarkus.runtime.StartupEvent;
import io.taaja.models.generic.Coordinates;
import io.taaja.models.generic.LocationInformation;
import io.taaja.models.record.spatial.SpatialEntity;
import io.taaja.models.views.SpatialRecordView;
import lombok.SneakyThrows;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.event.Observes;
import javax.transaction.Status;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.QueryParam;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public abstract class AbstractIntersectingExtensionsService extends AbstractService{

    @ConfigProperty(name = "service.purple-tiger.url", defaultValue = "https://purpletiger.taaja.io")
    protected String url;

    @ConfigProperty(name = "service.purple-tiger.timeout", defaultValue="-1")
    protected int timeout;

    public static final String VERSION = "v1";

    protected ObjectWriter objectWriter;

    protected HttpClient client;
//    protected RequestConfig requestConfig;
    protected ObjectReader objectReader;

    public void onStart(@Observes StartupEvent ev) {
        this.objectWriter = new ObjectMapper().writerWithView(SpatialRecordView.Coordinates.class);
        this.objectReader = new ObjectMapper().readerFor(LocationInformation.class);
        this.client = HttpClient.newHttpClient();
    }

    @SneakyThrows
    public LocationInformation calculate(SpatialEntity spatialEntity){


        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(new URI(this.url + "/" + AbstractIntersectingExtensionsService.VERSION + "/calculate/intersectingExtensions"))
                .POST(HttpRequest.BodyPublishers.ofByteArray(objectWriter.writeValueAsBytes(spatialEntity)))
                .headers("Content-Type", "application/json");

        if(this.timeout > -1){
            builder.timeout(Duration.ofSeconds(this.timeout));
        }

        HttpResponse<String> response = this.client.send(
                builder.build(),
                HttpResponse.BodyHandlers.ofString()
        );


        if(response.statusCode() != 200) {
            throw new BadRequestException("cant resolve coordinates");
        }

        LocationInformation locationInformation = objectReader.readValue(
                response.body()
        );
        locationInformation.setOriginator(spatialEntity);

        return locationInformation;
    }

    @SneakyThrows
    public LocationInformation calculate(Coordinates coordinates) {
        return this.calculate(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getAltitude());
    }

    @SneakyThrows
    public LocationInformation calculate(
            @QueryParam("longitude") float longitude,
            @QueryParam("latitude") float latitude,
            @QueryParam("altitude") Float altitude
    ){

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(this.url + "/" + AbstractIntersectingExtensionsService.VERSION +
                        "/encode/position?longitude=" + longitude + "&latitude=" + latitude + (altitude == null ? "" : "&altitude=" + altitude)))
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString()
        );

        if(response.statusCode() != 200) {
            throw new BadRequestException("cant resolve coordinates");
        }

        return objectReader.readValue(
                response.body()
        );
    }

}
