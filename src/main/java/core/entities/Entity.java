package core.entities;

import com.google.gson.*;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Base Entity
 * By Erik Helmers, the 21/05/2018
 */

public abstract class Entity<T extends Entity.Id> {

    public final T id;

    protected Entity(T id) {
        this.id = id;
    }


    public static abstract class Id<T> implements Serializable {

        protected final T _id;

        protected Id(T id) {
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
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(obj);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


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

