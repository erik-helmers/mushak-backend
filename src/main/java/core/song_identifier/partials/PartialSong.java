package core.song_identifier;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PUBLIC)
public class Result {

    @Builder
    public Result(String title, String album, String artist, String genre, int trackNo, int totalTrack, int year, int duration, String artPath) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.trackNo = trackNo;
        this.totalTrack = totalTrack;
        this.year = year;
        this.duration = duration;
        this.artPath = artPath;
    }

    String title;
    String album;
    String artist;

    String genre;

    int trackNo;
    int totalTrack;
    int year;
    int duration;

    String artPath;

}
