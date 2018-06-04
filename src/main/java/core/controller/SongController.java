package core.controller;

import core.databasemanager.music.MusicDatabase;
import core.entities.Entity;
import core.entities.mediatypes.Song;
import core.providers.SongProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Song get related
 * By Erik Helmers, the 21/05/2018
 */

@RestController
public class SongController {

    private final SongProvider song_provider;

    @Autowired
    public SongController(SongProvider song_provider) {
        this.song_provider = song_provider;
    }

    @RequestMapping("/song/{id}")
    public void song(@PathVariable int id,
                     HttpServletRequest request,
                     HttpServletResponse response) throws Exception{

        song_provider.song(id, request, response);

    }




}
