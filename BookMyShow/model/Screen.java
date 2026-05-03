package LLDDesigns.BookMyShow.model;

import LLDDesigns.BookMyShow.Enum.SeatType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Screen {
    private final String screenName;
    private final Map<SeatType, List<Seat>> seatTypeListMap;
    private Movie movie;

    public Screen(Builder builder){
        this.screenName = builder.screenName;
        this.seatTypeListMap = builder.seatTypeListCreator;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public String getScreenName(){
        return this.screenName;
    }

    public Map<SeatType, List<Seat>> getSeatTypeListMap() {
        return this.seatTypeListMap;
    }

    public Movie getMovie() {
        return this.movie;
    }

    //Builder Pattern
    public static class Builder{
        private final String screenName;
        private final Map<SeatType, Integer> seatTypeCountMap = new HashMap<>();
        Map<SeatType, List<Seat>> seatTypeListCreator = new HashMap<>();
        public Builder(String screenName){
            this.screenName = screenName;
        }

        public Builder addSeats(SeatType seatType, int count){
            if(count <= 0){
                System.out.println("At least one seat should present");
                return this;
            }
            seatTypeCountMap.put(seatType, count);
            return this;
        }

        public Screen build(){
            for(Map.Entry<SeatType, Integer> seatMap : seatTypeCountMap.entrySet()){
                List<Seat> seats = new ArrayList<>();
                int numbers = seatMap.getValue();
                SeatType seatType = seatMap.getKey();
                for(int i = 1;  i <= numbers; i++){
                    seats.add( SeatFactory.createSeat(i, seatType));
                }
                seatTypeListCreator.put(seatMap.getKey(), seats);
            }
            return new Screen(this);
        }
    }
}
