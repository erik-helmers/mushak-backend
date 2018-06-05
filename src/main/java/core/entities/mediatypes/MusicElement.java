package core.entities.mediatypes;

import core.entities.Entity;
import easysqlite.annotations.Column;
import lombok.Builder;
import lombok.ToString;


@ToString
public abstract class MusicElement extends Entity<MusicElement.Id> {

    @Column("name")
    public String name;

    protected MusicElement(){};
    protected MusicElement(Id id, String name) {
        super(id);
        this.name = name;
    }


    public static class Id extends Entity.Id<Integer>{
        public Id(Integer id) {
            super(id);
        }
    }


}
