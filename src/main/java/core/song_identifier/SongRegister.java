package core.song_identifier;

import core.databasemanager.music.MusicDatabase;
import core.entities.mediatypes.Album;
import core.entities.mediatypes.Artist;
import core.entities.mediatypes.Genre;
import core.entities.mediatypes.Song;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SongRegister {

    private final MusicDatabase music_db;

    @Autowired
    public SongRegister(MusicDatabase music_db){
        this.music_db = music_db;
    }



}
