package core.entities;

import com.google.gson.*;
import core.entities.mediatypes.MusicElement;
import core.entities.mediatypes.Song;
import easysqlite.annotations.declarations.ConverterTarget;
import easysqlite.annotations.declarations.IdColumn;
import easysqlite.serialization.Converter;
import logger.Log;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Base Entity
 * By Erik Helmers, the 21/05/2018
 */

public abstract class Entity<T extends Entity.Id> {

    @IdColumn
    public T id;

    public Class children;

    protected Entity(){}
    protected Entity(T id) {
        this.id = id;
    }

    //region id
    // ================================================ ID ================================================


    public static abstract class Id<T> implements Serializable{

        protected final T _id;

        public Id(T id) {
            this._id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Id)
                return getClass().equals(o.getClass()) && _id.equals(((Id) o)._id);
            return false;
        }
        @Override
        public String toString() {
            return _id.toString();
        }
        @Override
        public int hashCode() {
            return _id.hashCode();
        }

        public static Id fromString(String from, Class target){
            throw new IllegalStateException("this shouldn't be called");
        }
    }
    //endregion




    @Override
    public boolean equals(Object obj) {
        return id.equals(obj);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }


    //region serialization
    // ========================================== SERIALIZATION ===========================================

    @ConverterTarget(Id.class)
    public static Converter idConverter = new Converter(
            Object::toString,
            (from, target, source) -> {
                try {
                    target = Arrays.stream(source.getDeclaredClasses())
                            .filter(Id.class::isAssignableFrom)
                            .findFirst()
                            .get();
                    Method method = target.getMethod("fromString", String.class, Class.class);
                    return method.invoke(null, from, target);
                } catch (NoSuchMethodException e) {
                    throw new Error("A media type should have a constructor of type T(Integer) !", e);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new Error(e);
                }
            },
            true
    );
    //endregion

    private static class IdAdapter implements JsonSerializer<Id<?>> {

        @Override
        public JsonElement serialize(Id<?> entity, Type type, JsonSerializationContext ctx) {
            return ctx.serialize(entity._id);
        }


    }

    public String toJson(){
        return gson.toJson(this);
    }
    public final static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeHierarchyAdapter(Id.class, new IdAdapter()).create();
}

