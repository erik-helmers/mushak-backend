package core.databasemanager.music;


import core.Settings;
import core.databasemanager.DatabaseModule;
import core.entities.mediatypes.Album;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Genre;
import core.entities.mediatypes.Song;
import core.entities.utils.SongUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
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

        FileHandler result = new FileHandler();
        try {

            Files.walkFileTree(Paths.get(settings.get(Settings.MUSIC_DIRECTORY)),
                    result);
            register_from_paths(result.files);

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
