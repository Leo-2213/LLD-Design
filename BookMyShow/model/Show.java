package LLDDesigns.BookMyShow.model;

public class Show {
    private final int showId;
    private final Theater theater;
    private final Screen screen;
    private final double startTime;
    private final double endTime;
    private Movie movie;

    public Show(int showId, Theater theater, Screen screen, double startTime, double endTime, Movie movie) {
        this.showId = showId;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
    }
    public int getShowId(){
        return this.showId;
    }
    public Theater getTheater() {
        return this.theater;
    }

    public Screen getScreen() {
        return this.screen;
    }

    public double getStartTime() {
        return this.startTime;
    }

    public double getEndTime() {
        return this.endTime;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
