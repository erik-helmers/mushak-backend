package core.entities.mediatypes;




import easysqlite.annotations.declarations.Column;
import easysqlite.annotations.declarations.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Representation of a song, immutable
 * those classes shouldnt have any logic
 * they are just... dumb
 * By Erik Helmers, the 21/05/2018
 */

@Table("songs")
@FieldDefaults(level=AccessLevel.PUBLIC)
public class Song extends MusicElement {

    @Column
    String path;
    Album.Id album;
    Artist.Id artist;
    Artist.Id[] featuring;
    int track_no;
    int duration;

    public Song(){};
    @Builder
    public Song(Integer id,
                String title,
                String path,
                Album.Id album,
                Artist.Id artist,
                Artist.Id[] featuring,
                int track_no, int duration) {
        super(new Id(id), title);
        this.path = path;
        this.album = album;
        this.artist = artist;
        this.featuring = featuring;
        this.track_no = track_no;
        this.duration = duration;
    }



    public final static class Id extends MusicElement.Id{
        public Id(Integer id) {
            super(id);
        }
    }


}
