package core.entities.mediatypes;

import core.entities.Entity;
import lombok.Builder;

/**
 * Genre representation
 * By Erik Helmers, the 23/05/2018
 */

public class Genre extends Entity<Genre.Id> {

    public final String name;

    @Builder
    protected Genre(Integer id, String name) {
        super(new Id(id!=null?id:0));
        this.name = name;
    }

    public final static class Id extends Entity.Id<Integer>{
        protected Id(Integer id) {
            super(id);
        }
    }




}
