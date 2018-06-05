package core.entities.mediatypes;

import core.entities.Entity;
import easysqlite.annotations.Table;
import lombok.Builder;

/**
 * Genre representation
 * By Erik Helmers, the 23/05/2018
 */

@Table("genres")
public class Genre extends MusicElement {


    @Builder
    public Genre(Integer id, String name) {
        super(new Id(id), name);
    }

    public final static class Id extends MusicElement.Id{
        protected Id(Integer id) {
            super(id);
        }
    }




}
