package io.taaja;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.UuidRepresentation;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mongojack.JacksonMongoCollection;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import static com.mongodb.client.model.Filters.eq;

public abstract class AbstractRepository<T> {

    @Inject
    public MongoClient mongoClient;

    @ConfigProperty(name = "app.database")
    public String database;

    protected final String collectionName;
    protected final Class valueType;

    public AbstractRepository(String collectionName, Class valueType) {
        this.collectionName = collectionName;
        this.valueType = valueType;
    }

    protected MongoCollection<T> getCollection(){
        return JacksonMongoCollection.builder().build(mongoClient, this.database, this.collectionName, this.valueType, UuidRepresentation.STANDARD);
    }

    public T findById(String id){
        return getCollection().find(eq("_id", id)).first();
    }

    public FindIterable<T> findAll(){
        return getCollection().find();
    }

    public T findByIdOrException(String id){
        T entity = this.findById(id);
        if(entity == null){
            throw new NotFoundException("entity with id '" + id + "' does not exist in " + collectionName);
        }
        return entity;
    }

    public InsertOneResult insertOne(T document){
        return this.getCollection().insertOne(document);
    }

    public DeleteResult deleteById(String Id) {
        return getCollection().deleteOne(eq("_id", Id));
    }


    public UpdateResult update(String Id, T entity) {
        return this.getCollection().replaceOne(
                Filters.eq("_id", Id),
                entity
        );
    }

    public T deleteOneByIdAndGet(String Id) {
        return this.getCollection().findOneAndDelete(Filters.eq("_id", Id));
    }

}
