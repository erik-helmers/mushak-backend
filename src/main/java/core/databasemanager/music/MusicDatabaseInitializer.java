package core.databasemanager.music;


import core.Settings;

import core.entities.utils.SongUtils;
import core.song_identifier.SongRegister;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import java.util.List;

public class MusicDatabaseInitializer {

    Settings settings;
    MusicDatabase parent;

    public MusicDatabaseInitializer(
            MusicDatabase musicDatabase
    ) {
        settings = null;
        parent =  musicDatabase;
    }

    class FileHandler extends SimpleFileVisitor<Path> {

        final List<Path> files;
        public FileHandler(){
            super();
            files = new ArrayList<>();
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr){

            if (SongUtils.isMusicFile(file.toString())){
                files.add(file);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    /* Builds database :
     * iterates over all each file in music-dir
     * and passes them to *unknown* path to songs*/
    void build_database(){
        long startTime = System.nanoTime();

        File file = settings.getPath(Settings.Key.MUSIC_DIRECTORY).toFile();

        if (!file.exists() || !file.isDirectory()){
            throw new Error("music-dir should be a directory");
        }

        FileHandler result = new FileHandler();
        try {

            Files.walkFileTree(Paths.get(settings.get(Settings.Key.MUSIC_DIRECTORY)),
                    result);
            new SongRegister(parent).register(result.files);

        } catch (IOException e) {
            throw new Error("IO ERROR", e);
        }

        long endTime = System.nanoTime();

    }





}
