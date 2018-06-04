package core.entities.utils;

import core.Settings;
import core.entities.mediatypes.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * USAGE HERE
 * By Erik Helmers, the 21/05/2018
 */

@Service
public class SongUtils {

    private final String music_db;

    @Autowired
    public SongUtils(Settings settings){
        music_db = settings.get(Settings.MUSIC_DIRECTORY);
    }

    static String[] music_ext = {".mp3", ".m4a", ".flac"};

    public static boolean isMusicFile(String name) {

        for (String ext : music_ext) {
            if (name.endsWith(ext))
                return true;
        }
        return false;
    }

    public static Path toPath(Song song){
        Path path = Paths.get(song.path);
        return path.toAbsolutePath();
    }






}
