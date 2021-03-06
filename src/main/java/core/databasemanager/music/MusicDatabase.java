package core.databasemanager.music;

import core.Settings;
import core.entities.mediatypes.Album;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Genre;
import core.entities.mediatypes.Song;
import easysqlite.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.sql.Connection;

@org.springframework.stereotype.Repository
public class MusicDatabase {

    //TODO: detect music-dir change
    private static Resource populate = new ClassPathResource("/database/music_create.sql");

    boolean rebuild_on_launch = false;

    public final Repository<Song> songs;
    public final Repository<Album> albums;
    public final Repository<Artist> artists;
    public final Repository<Genre> genres;


    @Autowired
    public MusicDatabase(Settings settings) throws Exception{

        Connection connection = Repository.create_connection(settings.get(Settings.Key.MUSHAK_DB), populate.getFile());

        songs = new Repository<>(Song.class, connection);
        albums = new Repository<>(Album.class, connection);
        artists = new Repository<>(Artist.class, connection);
        genres = new Repository<>(Genre.class, connection);

        if (settings.getBoolean(Settings.Key.UPDATE_DB_ON_LOAD)) {
            new MusicDatabaseInitializer(this).build_database();
        }


    }










}







