package core.entities.mediatypes;

import core.entities.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Representation of a song, immutable
 * By Erik Helmers, the 21/05/2018
 */

@FieldDefaults(level=AccessLevel.PUBLIC)
public class Song extends Entity<Song.Id> {

    String title;
    String path;

    Album.Id album;
    Artist.Id artist;
    Artist.Id[] featuring;

    int track_no;
    int duration;

    @Builder
    public Song(Integer id,
                String title,
                String path,
                Album.Id album,
                Artist.Id artist,
                Artist.Id[] featuring,
                int track_no, int duration) {
        super(new Id(id));
        this.title = title;
        this.path = path;
        this.album = album;
        this.artist = artist;
        this.featuring = featuring;
        this.track_no = track_no;
        this.duration = duration;
    }



    public final static class Id extends Entity.Id<Integer>{
        public Id(int id) {
            super(id);
        }
    }

    public static Song resultSetToSong(ResultSet rs) throws SQLException {
        return Song.builder()
                .id(rs.getInt("id"))
                .title(rs.getString("title"))
                .path(rs.getString("path"))
                .build();
    }





}
