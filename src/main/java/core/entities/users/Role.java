package core.entities.users;

import core.entities.Entity;
import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.ConverterTarget;
import easysqlite.annotations.declarations.Table;
import easysqlite.serialization.Converter;

@Table("roles")
public class Role extends Entity<Role.Id> {

    public Role(){}
    public Role(Type role){
        this.type = role;
    }


    public Type type;
    @Column
    public String name;

    public enum Type {
        NOOB,
        USER,
        MODO,
        ADMIN,
        GOD;
    }


    @ConverterTarget(Type.class)
    public static Converter roleConverter = new Converter(
            o -> ((Type)o).name(),
            (from, target, source) -> Type.valueOf(from)
    );


    public static class Id extends Entity.IntegerId{
        public Id(Integer id) {
            super(id);
        }
    }
}
