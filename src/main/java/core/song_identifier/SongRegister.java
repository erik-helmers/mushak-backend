package core.song_identifier;

import core.databasemanager.music.MusicDatabase;
import core.song_identifier.fallbacks.BasicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

@Service
public class SongRegister {

    private final MusicDatabase music_db;

    @Autowired
    public SongRegister(MusicDatabase music_db){
        this.music_db = music_db;
    }

    public void register(List<Path> paths){
        BasicId basic = new BasicId();
        try{
            for (Path path : paths){
                music_db.songs.save(basic.song(path));
            }

        } catch (SQLException e){
            throw new Error(e);
        }

    }


}
