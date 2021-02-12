package io.taaja.models;

import com.fasterxml.jackson.databind.ObjectMapper;

class ModelTest {

    ObjectMapper objectMapper = new ObjectMapper();

//    static KafkaMessage getKafkaMessage(){
//        PositionUpdate positionUpdate = new PositionUpdate();
//        Coordinates coordinates = new Coordinates();
//        coordinates.setLongitude(123);
//        coordinates.setLatitude(345);
//        coordinates.setAltitude(null);
//        positionUpdate.setPosition(coordinates);
//        return new SpatialDataUpdate().addActuatorData(UUID.randomUUID().toString(), positionUpdate);
//    }
//
//    static SpatialEntity getSpatialEntity(){
//        Area area = new Area();
//        area.setElevation(123f);
//        area.setId(UUID.randomUUID().toString());
//        return area;
//    }
//
//
//    @Test
//    @JsonView(SpatialRecordView.Identity.class)
//    void testVisability() throws IOException {
//
//        PositionUpdate positionUpdate = new PositionUpdate();
//        Coordinates coordinates = new Coordinates();
//        coordinates.setLatitude(1000f);
//        coordinates.setLatitude(11.12f);
//        coordinates.setLongitude(46.34f);
//        positionUpdate.setPosition(coordinates);
//        SpatialDataUpdate spatialDataUpdate = new SpatialDataUpdate().addActuatorData(UUID.randomUUID().toString(), positionUpdate);
//        Map<String, Object> actuators = spatialDataUpdate.getActuators();
//
//        Corridor corridor = new Corridor();
//        corridor.setId(UUID.randomUUID().toString());
//        corridor.setShape(Corridor.ShapeType.Circular);
//        corridor.setValidFrom(new Date());
//        corridor.setValidUntil(new Date());
//        corridor.setPriority(ExtensionPriority.VERY_HIGH_PRIORITY);
//
//
//        List<Waypoint> waypoints = new ArrayList<>();
//
//        Waypoint waypoint = new Waypoint();
//        waypoint.setAltitude(1000f);
//        waypoint.setLatitude(11.12f);
//        waypoint.setLongitude(46.34f);
//        waypoint.setAdditionalData("additional data");
//        waypoints.add(waypoint);
//
//        corridor.setCoordinates(waypoints);
//        corridor.setActuators(actuators);
//
//        Area area = new Area();
//        area.setId(UUID.randomUUID().toString());
//        area.setElevation(123f);
//        area.setHeight(456f);
//        area.setValidFrom(new Date());
//        area.setValidUntil(new Date());
//        area.setPriority(ExtensionPriority.HIGH_PRIORITY);
//        List<List<LongLat>> lll = new ArrayList<>();
//        List<LongLat> longlats = new ArrayList<>();
//        lll.add(longlats);
//        LongLat longLat = new LongLat();
//        longLat.setLatitude(11.11f);
//        longLat.setLongitude(46.46f);
//        longlats.add(longLat);
//        longlats.add(longLat);
//        longlats.add(longLat);
//        area.setCoordinates(lll);
//        area.setActuators(actuators);
//
//
//        LocationInformation locationInformation = new LocationInformation();
//        List<SpatialEntity> spatialEntities = locationInformation.getSpatialEntities();
//        spatialEntities.add(corridor);
//        spatialEntities.add(area);
//        locationInformation.setAltitude(123f);
//        locationInformation.setLatitude(456f);
//        locationInformation.setLongitude(789f);
//
//
//        String valueAsString = objectMapper.writerWithView(SpatialRecordView.Identity.class).writeValueAsString(locationInformation);
//
//        LocationInformation locationInformationFromString = objectMapper.readValue(valueAsString, LocationInformation.class);
//
//        for(SpatialEntity spatialEntity : locationInformationFromString.getSpatialEntities()){
//            Assertions.assertTrue(spatialEntity.getActuators() == null && spatialEntity.getSamplers() == null && spatialEntity.getSensors() == null);
//        }
//
//    }
//
//    @Test
//    void testPriority(){
//        SpatialEntity defaultArea = getSpatialEntity();
//        defaultArea.setPriority(MAX_PRIORITY);
//        defaultArea.setExtensionBehaviour(ExtensionBehaviour.DEFAULT);
//
//        SpatialEntity trafficZone = getSpatialEntity();
//        trafficZone.setPriority(0);
//        trafficZone.setExtensionBehaviour(ExtensionBehaviour.TRAFFIC_ZONE);
//
//        SpatialEntity exclusionZone = getSpatialEntity();
//        exclusionZone.setPriority(0);
//        exclusionZone.setExtensionBehaviour(ExtensionBehaviour.EXCLUSION_ZONE);
//
//        LocationInformation locationInformation = new LocationInformation();
//        locationInformation.addSpatialEntity(trafficZone);
//        locationInformation.addSpatialEntity(exclusionZone);
//        locationInformation.addSpatialEntity(defaultArea);
//
//
//        List<SpatialEntity> spatialEntities = locationInformation.getSpatialEntities();
//
//        SpatialEntity lowPrio = spatialEntities.get(0);
//        SpatialEntity midPrio = spatialEntities.get(1);
//        SpatialEntity highPrio = spatialEntities.get(2);
//
//        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
//        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());
//
//        lowPrio.setPriority(0);
//        midPrio.setPriority(MAX_PRIORITY);
//
//        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
//        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());
//
//        midPrio.setPriority(0);
//        highPrio.setPriority(MAX_PRIORITY);
//
//        Assertions.assertTrue(lowPrio.calculateTotalPriority() < midPrio.calculateTotalPriority());
//        Assertions.assertTrue(midPrio.calculateTotalPriority() < highPrio.calculateTotalPriority());
//
//    }
//
//
//    @Test
//    void testPositionUpdate() throws IOException {
//
//        KafkaMessage kafkaMessage = getKafkaMessage();
//        String valueAsString = objectMapper.writeValueAsString(kafkaMessage);
//
//        SpatialDataUpdate spatialDataUpdateFromString = objectMapper.readValue(valueAsString, SpatialDataUpdate.class);
//        KafkaMessage kafkaMessageFromString = objectMapper.readValue(valueAsString, KafkaMessage.class);
//
//        try{
//             objectMapper.readValue(
//                     "{\"messageChannel\":\"spatialGuguGagaDataUpdate\",\"actuators\":{\"rand id\":{\"type\":\"PositionUpdate\",\"modified\":1588267958653,\"position\":{\"latitude\":345.0,\"longitude\":123.0,\"altitude\":null}}}}",
//                     KafkaMessage.class
//             );
//            Assertions.fail();
//        }catch (InvalidTypeIdException ei){
//        }
//
//    }
//
//    @Test
//    void testFallback() throws IOException {
//        KafkaMessage kafkaMessage = objectMapper.readValue(
//                "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\":{\"rand id\":{\"modified\":1588267958653,\"position\":{\"latitude\":345.0,\"longitude\":123.0,\"altitude\":null}}}}",
//                KafkaMessage.class
//        );
//
//        Assertions.assertTrue(kafkaMessage instanceof SpatialDataUpdate);
//
//        SpatialDataUpdate spatialDataUpdate = (SpatialDataUpdate) kafkaMessage;
//
//        Object next = spatialDataUpdate.getActuators().values().iterator().next();
//
//    }
//
//
//
//    @Test
//    void testRecord() throws IOException {
//
//        SpatialEntity spatialEntity = getSpatialEntity();
//        String valueAsString = objectMapper.writeValueAsString(spatialEntity);
//
//        Area areaFromString = objectMapper.readValue(valueAsString, Area.class);
//        SpatialEntity spatialEntityFromString = objectMapper.readValue(valueAsString, SpatialEntity.class);
//
//        try{
//            objectMapper.readValue(valueAsString, Corridor.class);
//            Assertions.fail();
//        }catch (IllegalArgumentException ei){
//        }
//
//    }
//
//    @Test
//    void testSpatialMerge() throws IOException {
//
//
//        SpatialEntity spatialEntity = getSpatialEntity();
//
//        ObjectReader updater = objectMapper.readerForUpdating(spatialEntity);
//
//        KafkaMessage kafkaMessage = getKafkaMessage();
//        String valueAsString = objectMapper.writeValueAsString(kafkaMessage);
//
//        Object updatedExtension = updater.readValue(valueAsString);
//
//        //parse to validate
//        SpatialEntity abstractExtension= objectMapper.convertValue(updatedExtension, SpatialEntity.class);
//
//        String faulty = "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\": {}}";
//
//        KafkaMessage faultyMsg = objectMapper.readValue(
//                faulty,
//                KafkaMessage.class
//        );
//
//        updater.readValue(faulty);
//
//        //parse to validate
//        SpatialEntity abstractUpdatedExtension= objectMapper.convertValue(updatedExtension, SpatialEntity.class);
//
//        Assertions.assertTrue(abstractUpdatedExtension.equals(abstractExtension));
//    }
//
//    @Test
//    void validateKafkaMessage() throws IOException {
//
//        try{
//            KafkaMessage kafkaMessage = objectMapper.readValue(
//                    "{\"messageChannel\":\"spatialDataUpdate\",\"actuators\": false}",
//                    KafkaMessage.class
//            );
//            Assertions.fail();
//        }catch (MismatchedInputException me){
//
//        }
//
//    }
//
//
//    @Test
//    void spatialOperation() throws IOException {
//        SpatialEntityOperation spatialEntityOperation = new SpatialEntityOperation();
//        spatialEntityOperation.setOperationType(OperationType.Altered);
//        spatialEntityOperation.setTargetId(UUID.randomUUID().toString());
//        spatialEntityOperation.setIntersectingExtensions(new ArrayList<>());
//
//        String valueAsString = objectMapper.writeValueAsString(spatialEntityOperation);
//
//        KafkaMessage kafkaMessageFromString = objectMapper.readValue(valueAsString, KafkaMessage.class);
//        SpatialEntityOperation spatialEntityOperationFromString = objectMapper.readValue(valueAsString, SpatialEntityOperation.class);
//
//    }


}