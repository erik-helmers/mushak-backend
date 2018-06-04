package core.entities.users;

import core.entities.Entity;
import core.entities.mediatypes.Album;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Song;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * User
 * By Erik Helmers, the 26/05/2018
 */

@ToString
@FieldDefaults(level=AccessLevel.PUBLIC)
public class User extends Entity<User.Id>{

    @Builder
    public User(@NonNull Long id,
                @NonNull String username,
                @NonNull String password,
                Set<Song.Id> songs, Set<Album.Id> albums, Set<Artist.Id> artists) {
        super(new Id(id));
        this.username = username;
        this.password = password;
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
    }

    public static final class Id extends Entity.Id<Long> {
        public Id(Long id) {
            super(id);
        }
    }

    final String username;
    final String password;
    Set<Song.Id> songs;
    Set<Album.Id> albums;
    Set<Artist.Id> artists;

    public static User fromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .build();
    }
}
