package LLDDesigns.BookMyShow.model;

import java.time.Duration;

public class Movie {
    private final String movieName;
    private final int duration;

    public String getMovieName() {
        return movieName;
    }

    public int getDuration() {
        return duration;
    }

    public Movie( String movieName, int duration) {
        this.movieName = movieName;
        this.duration = duration;
    }
}
