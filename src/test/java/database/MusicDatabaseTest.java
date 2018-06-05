package database;

import core.databasemanager.music.MusicDatabase;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Genre;
import core.entities.mediatypes.Song;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
/*
@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MusicDatabaseTest {

    Path path = Paths.get("Music Sample/FloFilz - Basement Jazz (Feat. Blu).mp3");

    // WARNING ! ! Expects a user of id 0, name : user, password : password.
    @Autowired
    private MusicDatabase db;

    @Configuration
    @ComponentScan("core")
    public static class Config{}



    @Test
    public void write_song(){
        Song song = Song.builder().id(0).title("Ayy").path("song.mp3").build();
        db.write.song(song);
    }

    @Test
    public void write_genre(){
        Genre genre = Genre.builder().id(0).name("Rap Français").build();
        db.write.genre(genre);
    }

    @Test
    public void write_artist(){
        Artist artist = Artist.builder().id(0).name("Orelsan").build();
        db.write.artist(artist);
    }

}*/