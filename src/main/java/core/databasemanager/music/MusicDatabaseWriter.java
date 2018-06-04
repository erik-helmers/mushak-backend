package core.databasemanager.music;

import core.Settings;
import core.databasemanager.DatabaseModule;
import core.entities.mediatypes.Album;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Genre;
import core.entities.mediatypes.Song;

import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MusicDatabaseWriter give WRITE
 * By Erik Helmers, the 23/05/2018
 */

//TODO: broke read
public class MusicDatabaseWriter extends DatabaseModule<MusicDatabase> {

    public MusicDatabaseWriter(MusicDatabase database) {
        super(database);
    }

    public void songs(List<Song> songs){

        // addSongs:  optimized for multiple adds

        String sql = "INSERT INTO songs(title, path) VALUES(?,?)";
        Path music_dir = settings.getPath(Settings.MUSIC_DIRECTORY);

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            int i = 0;

            for (Song f : songs) {
                pstmt.setString(1, f.title);
                pstmt.setString(2, f.path);
                pstmt.addBatch();
                i++;

                if (i%1000 == 0 || i == songs.size()) {
                    pstmt.executeBatch();
                }
            }
        } catch (SQLException e){
            throw new Error(e.getMessage());
        }
    }

    public void song(Song song){
        songs(Collections.singletonList(song));
    }

    public void artist(Artist artist){
        String sql = "INSERT INTO artists(title, songs,albums, metadata) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, artist.name);
            pstmt.setString(2, artist.songs.toString());
            pstmt.setString(3, artist.albums.toString());
            pstmt.setString(4, artist.metadata);
            pstmt.execute();
        } catch (SQLException e){
            throw new Error(e);
        }
    }

    public void album(Album album){
        String sql = "INSERT INTO albums(title, songs, artist, genre, year, total_track, metadata) VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, album.name);
            pstmt.setString(2, album.songs.toString());
            pstmt.setString(3, album.artist.toString());
            pstmt.setString(4, album.genre.toString());
            pstmt.setInt(5, album.release_year);
            pstmt.setInt(6, album.songs.size());
            pstmt.setString(7, album.metadata);
            pstmt.execute();
        } catch (SQLException e){
            throw new Error(e);
        }
    }

    public void genre(Genre genre){
        String sql = "INSERT INTO genres(title) VALUES(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1,genre.name);
            pstmt.execute();
        } catch (SQLException e){
            throw new Error(e);
        }
    }
}
