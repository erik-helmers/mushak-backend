package core.song_identifier.fallbacks;


import core.entities.mediatypes.Song;

import java.io.File;
import java.nio.file.Path;

//TODO: NO - BASIC - ALLOWED
@Deprecated
public class BasicId {


    public Song song(Path path){

        return Song.builder()
                .path(path.toAbsolutePath().toString())
                .title(path.getFileName().toString())
                .build() ;

    }


}
