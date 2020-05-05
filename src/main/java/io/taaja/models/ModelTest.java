package io.taaja.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import io.taaja.models.generic.Coordinates;
import io.taaja.models.generic.LocationInformation;
import io.taaja.models.message.KafkaMessage;
import io.taaja.models.message.data.update.SpatialDataUpdate;
import io.taaja.models.message.data.update.actuator.AbstractActuatorUpdate;
import io.taaja.models.message.data.update.actuator.PositionUpdate;
import io.taaja.models.message.extension.operation.OperationType;
import io.taaja.models.message.extension.operation.SpatialOperation;
import io.taaja.models.record.spatial.Area;
import io.taaja.models.record.spatial.Corridor;
import io.taaja.models.record.spatial.ExtensionBehaviour;
import io.taaja.models.record.spatial.SpatialEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.taaja.models.record.spatial.ExtensionPriority.MAX_PRIORITY;

class ModelTest {

    ObjectMapper objectMapper = new ObjectMapper();

    static KafkaMessage getKafkaMessage(){
        PositionUpdate positionUpdate = new PositionUpdate();
        Coordinates coordinates = new Coordinates();
        coordinates.setLongitude(123);
        coordinates.setLatitude(345);
        coordinates.setAltitude(null);
        positionUpdate.setPosition(coordinates);
        return new SpatialDataUpdate(UUID.randomUUID().toString(), positionUpdate);
    }

    static SpatialEntity getSpatialEntity(){
        Area area = new Area();
        area.setElevation(123f);
        area.setId(UUID.randomUUID().toString());
        return area;
    }


    @Test
    void testPriority(){
        SpatialEntity defaultArea = getSpatialEntity();
        defaultArea.setPriority(MAX_PRIORITY);
        defaultArea.setExtensionBehaviour(ExtensionBehaviour.Default);

        SpatialEntity trafficZone = getSpatialEntity();
        trafficZone.setPriority(0);
        trafficZone.setExtensionBehaviour(ExtensionBehaviour.TrafficZone);

        SpatialEntity exclusionZone = getSpatialEntity();
        exclusionZone.setPriority(0);
        exclusionZone.setExtensionBehaviour(ExtensionBehaviour.ExclusionZone);

        LocationInformation locationInformation = new LocationInformation();
        locationInformation.addSpatialEntity(trafficZone);
        locationInformation.addSpatialEntity(exclusionZone);
        locationInformation.addSpatialEntity(defaultArea);


        List<SpatialEntity> spatialEntities = locationInformation.getSpatialEntities();

        SpatialEntity lowPrio = spatialEntities.get(0);
        SpatialEntity midPrio = spatialEntities.get(1);
        SpatialEntity highPrio = spatialEntities.get(2);

        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());

        lowPrio.setPriority(0);
        midPrio.setPriority(MAX_PRIORITY);

        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());

        midPrio.setPriority(0);
        highPrio.setPriority(MAX_PRIORITY);

        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());

    }


    @Test
    void testPositionUpdate() throws IOException {

        KafkaMessage kafkaMessage = getKafkaMessage();
        String valueAsString = objectMapper.writeValueAsString(kafkaMessage);

        SpatialDataUpdate spatialDataUpdateFromString = objectMapper.readValue(valueAsString, SpatialDataUpdate.class);
        KafkaMessage kafkaMessageFromString = objectMapper.readValue(valueAsString, KafkaMessage.class);

        try{
             objectMapper.readValue(
                     "{\"messageChannel\":\"spatialGuguGagaDataUpdate\",\"actuators\":{\"rand id\":{\"type\":\"PositionUpdate\",\"modified\":1588267958653,\"position\":{\"latitude\":345.0,\"longitude\":123.0,\"altitude\":null}}}}",
                     KafkaMessage.class
             );
            Assertions.fail();
        }catch (InvalidTypeIdException ei){
        }

    }

    @Test
    void testFallback() throws IOException {
        KafkaMessage kafkaMessage = objectMapper.readValue(
                "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\":{\"rand id\":{\"modified\":1588267958653,\"position\":{\"latitude\":345.0,\"longitude\":123.0,\"altitude\":null}}}}",
                KafkaMessage.class
        );

        Assertions.assertTrue(kafkaMessage instanceof SpatialDataUpdate);

        SpatialDataUpdate spatialDataUpdate = (SpatialDataUpdate) kafkaMessage;

        AbstractActuatorUpdate next = spatialDataUpdate.getActuators().values().iterator().next();
        
    }



    @Test
    void testRecord() throws IOException {

        SpatialEntity spatialEntity = getSpatialEntity();
        String valueAsString = objectMapper.writeValueAsString(spatialEntity);

        Area areaFromString = objectMapper.readValue(valueAsString, Area.class);
        SpatialEntity spatialEntityFromString = objectMapper.readValue(valueAsString, SpatialEntity.class);

        try{
            objectMapper.readValue(valueAsString, Corridor.class);
            Assertions.fail();
        }catch (IllegalArgumentException ei){
        }

    }

    @Test
    void testSpatialMerge() throws IOException {


        SpatialEntity spatialEntity = getSpatialEntity();

        ObjectReader updater = objectMapper.readerForUpdating(spatialEntity);

        KafkaMessage kafkaMessage = getKafkaMessage();
        String valueAsString = objectMapper.writeValueAsString(kafkaMessage);

        Object updatedExtension = updater.readValue(valueAsString);

        //parse to validate
        SpatialEntity abstractExtension= objectMapper.convertValue(updatedExtension, SpatialEntity.class);

        String faulty = "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\": {}}";

        KafkaMessage faultyMsg = objectMapper.readValue(
                faulty,
                KafkaMessage.class
        );

        updater.readValue(faulty);

        //parse to validate
        SpatialEntity abstractUpdatedExtension= objectMapper.convertValue(updatedExtension, SpatialEntity.class);

        Assertions.assertTrue(abstractUpdatedExtension.equals(abstractExtension));
    }

    @Test
    void validateKafkaMessage() throws IOException {

        try{
            KafkaMessage kafkaMessage = objectMapper.readValue(
                    "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\": false}",
                    KafkaMessage.class
            );
            Assertions.fail();
        }catch (MismatchedInputException me){

        }

    }
    
    
    @Test
    void spatialOperation() throws IOException {
        SpatialOperation spatialOperation = new SpatialOperation();
        spatialOperation.setOperationType(OperationType.Altered);
        spatialOperation.setTargetId(UUID.randomUUID().toString());
        spatialOperation.setIntersectingExtensions(new ArrayList<>());

        String valueAsString = objectMapper.writeValueAsString(spatialOperation);

        KafkaMessage kafkaMessageFromString = objectMapper.readValue(valueAsString, KafkaMessage.class);
        SpatialOperation spatialOperationFromString = objectMapper.readValue(valueAsString, SpatialOperation.class);

    }


}