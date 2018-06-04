package core.databasemanager.music;

import core.Settings;
import core.databasemanager.Database;
import core.databasemanager.DatabaseModule;
import core.entities.mediatypes.Song;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static core.entities.mediatypes.Song.resultSetToSong;

/**
 * MusicDatabaseReader only give READ infos
 * By Erik Helmers, the 23/05/2018
 */

@Log4j2
public class MusicDatabaseReader extends DatabaseModule<MusicDatabase> {



    private static final String read_one = "SELECT id, title, path FROM songs WHERE id=?";
    private static final String read_all = "SELECT id, title, path FROM songs";

    public MusicDatabaseReader(MusicDatabase database) {
        super(database);
    }


    public Song song(Song.Id id) throws NoSuchElementException{

        try (PreparedStatement pstmt = connection.prepareStatement(read_one)){

            pstmt.setString(1,  id.toString());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return Song.resultSetToSong(rs);
            }
        } catch (SQLException e){
            throw new Error(e.getMessage());
        }
        throw new NoSuchElementException(id.toString());
    }

    @Deprecated
    public Song song(int id) throws NoSuchElementException{
        return song(Song.builder().id(id).build().id);
    }

    public List<Song> songs(){
        log.warn("THIS METHOD SHOULD BE FOR SHOWCASE ONLY !!!");
        List<Song> output = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(read_all);
             ResultSet rs = pstmt.executeQuery()){

            while (rs.next()){
                output.add(Song.resultSetToSong(rs));
            }

        } catch (SQLException e){
            log.error(e);
        }
        return output;
    }

    public

}
