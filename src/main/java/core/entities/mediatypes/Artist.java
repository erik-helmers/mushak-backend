package core.entities.mediatypes;

import core.entities.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Artist immutable representation
 * By Erik Helmers, the 23/05/2018
 */


@FieldDefaults(makeFinal = false, level = AccessLevel.PUBLIC)
public class Artist extends Entity<Artist.Id> {

    String name;
    List<Album.Id> albums = new ArrayList<>();
    List<Song.Id> songs = new ArrayList<>();
    String metadata;

    @Builder
    public Artist(Integer id,
                  String name,
                  List<Album.Id> albums, List<Song.Id> songs, String metadata) {
        super(new Id(id));
        this.name = name;
        this.albums = Optional.ofNullable(albums).orElse(new ArrayList<>());
        this.songs = Optional.ofNullable(songs).orElse(new ArrayList<>());


        this.metadata = metadata;
    }



    public final static class Id extends Entity.Id<Integer> {
        protected Id(Integer id) {
            super(id);
        }
    }
}
