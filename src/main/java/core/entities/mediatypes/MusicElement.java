package core.entities.mediatypes;

import core.entities.Entity;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.ConverterTarget;
import easysqlite.serialization.Converter;
import easysqlite.serialization.Deserialization;
import easysqlite.serialization.Serialization;
import logger.Log;
import lombok.Builder;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@ToString
public abstract class MusicElement extends Entity<MusicElement.Id> {

    @Column("name")
    public String name;

    protected MusicElement() {
    }

    ;

    protected MusicElement(Id id, String name) {
        super(id);
        this.name = name;
    }


    public static class Id extends Entity.Id<Integer> {
        public Id(Integer id) {
            super(id);
        }

        public static Entity.Id fromString(String from, Class target) {
            try {
                return (Entity.Id) target.getConstructor(Integer.class).newInstance(Integer.parseInt(from));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new Error("uh oh, something happened !", e);
            }

        }


    }
}
