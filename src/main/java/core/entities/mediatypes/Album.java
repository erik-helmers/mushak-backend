package core.entities.mediatypes;

import core.entities.Entity;
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

@FieldDefaults(makeFinal=true, level=AccessLevel.PUBLIC)
public class Album extends Entity<Album.Id> {

    String name;

    int release_year;
    int songs_number;
    List<Song.Id> songs;
    String metadata;
    Genre.Id genre;

    Artist.Id artist;
    List<Artist.Id> featuring;

    @Builder
    protected Album(@NonNull Integer id,
                    @NonNull String name,
                    int release_year,
                    int songs_number,
                    List<Song.Id> songs,
                    String metadata, Genre.Id genre,
                    Artist.Id artist,
                    List<Artist.Id> featuring){
        super(new Id(id));
        this.name = name;
        this.release_year = release_year;
        this.songs_number = songs_number;
        this.songs = songs != null ? songs : new ArrayList<>();
        this.metadata = metadata;

        this.genre = genre;
        this.artist = artist!=null ? artist : new Artist.Id(0);

        this.featuring = featuring;
    }

    public static final class Id extends Entity.Id<Integer>{
        protected Id(Integer id) {
            super(id);
        }
    }

}
