package core.entities;

import com.google.gson.*;
import core.entities.mediatypes.MusicElement;
import core.entities.mediatypes.Song;
import core.entities.users.MusicProfile;
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


    public static abstract class Id<T> implements Serializable, easysqlite.serialization.Serializable{

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
            return "UnknownTypeId("+_id.toString()+")";
        }
        @Override
        public int hashCode() {
            return _id.hashCode();
        }

        public static Id fromString(String from, Class target){
            throw new IllegalStateException("this shouldn't be called");
        }

        public String represent(){ return _id.toString();}

        @Override
        public String serialize() {
            Log.debug_call();
            return represent();
        }

        public static boolean propagate(){
            return true;
        }


        /*
        Deserialize an Id
         */
        public static Object deserialize(String from, Class target, Class source){

            try {
                //In case where the Id class was obfuscated by generic, find it by source class
                if (target.equals(Id.class)) {
                    target = Arrays.stream(source.getDeclaredClasses())
                            .filter(Id.class::isAssignableFrom)
                            .findFirst()
                            .orElseThrow(() -> new Error("there should be some Id class in an entity!"));
                }
                Log.debug_value("target", target);
                Method method = target.getMethod("fromString", String.class, Class.class);
                return method.invoke(null, from, target);
            } catch (NoSuchMethodException e) {
                throw new Error("WOW ! this state is pretty illegal", e);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new Error(e);
            }
            }
        }



    public static abstract class IntegerId extends Entity.Id<Integer>{

        public IntegerId(Integer id) {
            super(id);
        }

        public static Id fromString(String from, Class target){
            try {
                Log.debug_value("target", target);
                return (Id) target.getConstructor(Integer.class).newInstance(Integer.parseInt(from));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new Error("Uh oh something happened when calling child constructor !", e);
            }
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

