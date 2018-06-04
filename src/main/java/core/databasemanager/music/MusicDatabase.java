package core.databasemanager.music;

import core.Settings;
import core.databasemanager.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import core.entities.utils.SongUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MusicDatabase extends Database {

    //TODO: detect music-dir change
    private static Resource populate = new ClassPathResource("/database/music_create.sql");

    boolean rebuild_on_launch = false;

    public final MusicDatabaseReader read;
    public final MusicDatabaseWriter write;

    @Autowired
    public MusicDatabase(Settings settings) throws Exception{
        super(settings);
        connect(settings.get(Settings.MUSIC_DB_PATH), populate.getFile());

        read = new MusicDatabaseReader(this);
        write = new MusicDatabaseWriter(this);

        if (settings.getBoolean(Settings.UPDATE_DB_ON_LOAD)) {
            new MusicDatabaseInitializer(this).build_database();
        }

    }










}







