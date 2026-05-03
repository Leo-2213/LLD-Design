package LLDDesigns.BookMyShow.model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Ticket {
    private final Movie movie;
    private final Theater theater;
    private final Screen screen;
    //    private final double startTime;
//    private final double endTime;
    private final List<Seat> seatList;
    private final double amount;

    public Ticket(Movie movie, Theater theater, Screen screen, List<Seat> seatList, double amount) {
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
//        this.startTime = startTime;
//        this.endTime = endTime;
        this.seatList = seatList;
        this.amount = amount;
    }
    public Movie getMovie() {
        return movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        String seats = seatList == null
                ? "[]"
                : seatList.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));

        return "Ticket{" +
                " movie='" + movie.getMovieName() + '\'' +
                ", theater='" + theater.getTheaterName() + '\'' +
                ", screen='" + screen.getScreenName() + '\'' +
                ", seats=" + seats +
                ", amount=" + df.format(amount) +
                " }";
    }
}
