package core.databasemanager.music;


import core.Settings;
import core.databasemanager.DatabaseModule;

import core.entities.utils.SongUtils;
import core.song_identifier.SongRegister;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import java.util.List;

public class MusicDatabaseInitializer extends DatabaseModule<MusicDatabase> {


    public MusicDatabaseInitializer(MusicDatabase database) {
        super(database);
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
        System.out.println("====================== BEGINNING DB BUILD =========================");
        long startTime = System.nanoTime();

        File file = settings.getPath(Settings.MUSIC_DIRECTORY).toFile();

        if (!file.exists() || !file.isDirectory()){
            throw new Error("music-dir should be a directory");
        }
        FileHandler result = new FileHandler();
        try {

            Files.walkFileTree(Paths.get(settings.get(Settings.MUSIC_DIRECTORY)),
                    result);
            new SongRegister(parent).register(result.files);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Error("IO ERROR");
        }

        long endTime = System.nanoTime();
        System.out.println("================== DB BUILD ENDED SUCCESSFULLY ! ===================");
        System.out.println("==================---- Added "+String.valueOf(result.files.size())+ " songs ! ===================");
        System.out.println("==============--- took "+ String.valueOf((endTime-startTime)/1000000f)+ " ms ! ----===============");

    }





}
