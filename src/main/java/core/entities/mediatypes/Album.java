package core.entities.mediatypes;

import core.entities.Entity;
import easysqlite.annotations.Column;
import easysqlite.annotations.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * Album immutable representation
 * By Erik Helmers, the 23/05/2018
 */

@Table("albums")
@FieldDefaults(makeFinal=true, level=AccessLevel.PUBLIC)
public class Album extends MusicElement{

    @Column("release_year")
    int release_year;
    @Column("songs_number")
    int songs_number;
    @Column("songs")
    List<Song.Id> songs;
    @Column("metadata")
    String metadata;
    @Column("genre")
    Genre.Id genre;

    @Column("artist")
    Artist.Id artist;
    @Column("featuring")
    List<Artist.Id> featuring;

    @Builder
    protected Album(Integer id,
                    String name,
                    int release_year,
                    int songs_number,
                    List<Song.Id> songs,
                    String metadata, Genre.Id genre,
                    Artist.Id artist,
                    List<Artist.Id> featuring){
        super(new Id(id), name);
        this.release_year = release_year;
        this.songs_number = songs_number;
        this.songs = songs != null ? songs : new ArrayList<>();
        this.metadata = metadata;

        this.genre = genre;
        this.artist = artist!=null ? artist : new Artist.Id(0);

        this.featuring = featuring;
    }

    public static final class Id extends MusicElement.Id{
        public Id(Integer id) {
            super(id);
        }
    }

}
