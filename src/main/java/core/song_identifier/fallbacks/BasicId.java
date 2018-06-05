package core.song_identifier.fallbacks;


import core.entities.mediatypes.Song;

import java.io.File;
import java.nio.file.Path;

//TODO: NO - BASIC - ALLOWED
@Deprecated
public class BasicId {


    public Song song(Path path){
        System.out.println(path.getFileName().toString());
        System.out.println(
                Song.builder()
                .path(path.toAbsolutePath().toString())
                .title(path.getFileName().toString())
                .build() );
        return Song.builder()
                .path(path.toAbsolutePath().toString())
                .title(path.getFileName().toString())
                .build() ;



    }


}
