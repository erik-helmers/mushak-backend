package core.song_identifier.fallbacks;

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

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileTags {



    public Genre getGenre(Tag tag){
        return Genre.builder()
                .name(tag.getFirst(FieldKey.GENRE))
                .build();
    }

    public Artist getArtist(Tag tag){
        return Artist.builder()
                .name(tag.getFirst(FieldKey.ARTIST))
                .build();
    }

    public Album getAlbum(Tag tag, Artist.Id artistId, Genre.Id genreId){

        Album album = Album.builder()
                .id(0)
                .name(tag.getFirst(FieldKey.ALBUM_ARTIST))
                .release_year(getFirstInt(tag, FieldKey.YEAR))
                .genre(genreId)
                .songs_number(getFirstInt(tag, FieldKey.TRACK_TOTAL))
                .build();
        return album;


    }

    //TODO: hash strings to avoid case, accents and special characters similarities
    //TODO: prevent same song registering by selective recursive selection with nb files in dirs
    //TODO: generate unique id from rules

    int getFirstInt(Tag tag, FieldKey s){
        String x = getFirst(tag, s);
        if (x.equals("") || x.equals("null")) return -1;
        return Integer.parseInt(x);
    }

    String or(String tag, String default_str){
        return tag.equals("")  ? default_str : tag;
    }


    String getFirst(Tag tag, FieldKey fieldKey){
        String otpt = tag.getFirst(tag.getFirst(fieldKey));
        if (otpt==null || otpt.equals("")){
            throw new IllegalArgumentException("Missing tag");
        } return otpt;
    }

    void single(Path path) throws Exception{

        AudioFile f;
        try {
            f = AudioFileIO.read(path.toFile());
        } catch (TagException e){
            throw new IllegalArgumentException(e);
        } catch (IOException | CannotReadException | InvalidAudioFrameException | ReadOnlyFileException e) {
            throw new Exception(e);
        }

        Tag tag = f.getTag();
        AudioHeader ah = f.getAudioHeader();

        String title = getFirst(tag, FieldKey.TITLE);
        String artist_name = getFirst(tag, FieldKey.ARTIST);
        String album_name = getFirst(tag, FieldKey.ALBUM);
        String genre_name = getFirst(tag, FieldKey.GENRE);

        int track_no = getFirstInt(tag, FieldKey.DISC_NO);
        int duration = ah.getTrackLength();

        return Song.builder()
                .title(title)
                .



    }

    void register_from_paths(List<Path> paths){

        HashMap<String, Artist> artists = new HashMap<>();
        HashMap<String, Album> albums = new HashMap<>();
        HashMap<String, Genre> genres = new HashMap<>();
        List<Song> songs = new ArrayList<>();
        for (Path path: paths){
            try {
                AudioFile f = AudioFileIO.read(path.toFile());
                Tag tag = f.getTag();
                AudioHeader ah = f.getAudioHeader();

                String title = tag.getFirst(FieldKey.TITLE);
                String artist_name = tag.getFirst(FieldKey.ARTIST);
                String album_name = tag.getFirst(FieldKey.ALBUM);
                String genre_name = tag.getFirst(FieldKey.GENRE);
                int track_no = getFirstInt(tag, FieldKey.DISC_NO);


                if (!genres.containsKey(genre_name)){
                    Genre genre = getGenre(tag);
                    music_db.write.genre(genre);
                    genres.put(genre_name, genre);
                }
                if (!artists.containsKey(artist_name)){
                    Artist artist = getArtist(tag);
                    music_db.write.artist(artist);
                    artists.put(artist_name, artist);
                }
                if (!albums.containsKey(album_name)){
                    Artist artist = artists.get(artist_name);
                    Genre genre = genres.get(genre_name);
                    Album album =  getAlbum(tag, artist.id, genre.id);

                    albums.put(album_name, album);
                    //Register album to songs
                    artist.albums.add(album.id);
                    music_db.write.album(album);
                    System.out.println("\t Added : "+album_name);
                }


                Album album = albums.get(album_name);
                Artist artist = artists.get(artist_name);

                /*Song song = Song.builder()
                        .title(or(title, path.getFileName().toString()))
                        .album(album.id)
                        .artist(artist.id)
                        .path(path.toString())
                        .duration(ah.getTrackLength())
                        .track_no(track_no)
                        .build();
                */
                Song song = Song.builder()
                        .id(0)
                        .title(path.getFileName().toString())
                        .path(path.toAbsolutePath().toString())
                        .build();
                songs.add(song);


                album.songs.add(song.id);
                artist.songs.add(song.id);




            } catch (IOException e) {
                e.printStackTrace();
            } catch (CannotReadException e) {
                e.printStackTrace();
            } catch (ReadOnlyFileException e) {
                e.printStackTrace();
            } catch (TagException e) {
                e.printStackTrace();
            } catch (InvalidAudioFrameException e) {
                e.printStackTrace();
            }
        }

        music_db.write.songs(songs);




    }
}
