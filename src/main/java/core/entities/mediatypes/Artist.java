package core.entities.mediatypes;

import core.entities.Entity;
import easysqlite.annotations.Column;
import easysqlite.annotations.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Artist immutable representation
 * By Erik Helmers, the 23/05/2018
 */


@Table("artists")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Artist extends MusicElement {


    @Column
    List<Album.Id> albums;
    @Column
    List<Song.Id> songs;
    @Column
    String metadata;

    @Builder
    public Artist(Integer id,
                  String name,
                  List<Album.Id> albums, List<Song.Id> songs, String metadata) {
        super(new Id(id), name);

        this.albums = Optional.ofNullable(albums).orElse(new ArrayList<>());
        this.songs = Optional.ofNullable(songs).orElse(new ArrayList<>());


        this.metadata = metadata;
    }



    public final static class Id extends MusicElement.Id {
        public Id(Integer id) {
            super(id);
        }
    }
}
