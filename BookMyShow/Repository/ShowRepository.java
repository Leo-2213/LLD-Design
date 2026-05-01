package LLDDesigns.BookMyShow.Repository;

import LLDDesigns.BookMyShow.model.Movie;
import LLDDesigns.BookMyShow.model.Show;

import java.util.HashMap;
import java.util.List;

public class ShowRepository {
    private HashMap<String, List<Show>> showList = new HashMap<>();

    public void addShow(String movieName,  List<Show> shows){
        showList.put(movieName, shows);
    }

    public List<Show> getAllShowsByMovieName(String movieName){
        return showList.getOrDefault(movieName, null);
    }
    public Show getShowByMovieNameAndId(String movieName, int id){
        List<Show> shows = showList.get(movieName);
        for(Show show : shows){
            if(show.getShowId() == id){
                return show;
            }
        }
        return null;
    }
}

