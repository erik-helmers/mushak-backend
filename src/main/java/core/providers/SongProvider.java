package core.providers;


import core.databasemanager.music.MusicDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.MultipartFileSender;
import core.entities.utils.SongUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;

@Service
public class SongProvider {

    private final MusicDatabase music_db ;
    private final SongUtils utils;

    @Autowired
    public SongProvider(MusicDatabase music_db, SongUtils utils) {
        this.music_db = music_db;
        this.utils = utils;
    }

    public void song(int id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        Path path = SongUtils.toPath(music_db.songs.search("id", String.valueOf(id)).get(0));

        MultipartFileSender.fromPath(path)
                .with(request)
                .with(response)
                .serveResource();

    }
}

