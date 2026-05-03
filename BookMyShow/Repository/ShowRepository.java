package LLDDesigns.BookMyShow.Repository;

import LLDDesigns.BookMyShow.model.Movie;
import LLDDesigns.BookMyShow.model.Show;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ShowRepository {
    private HashMap<String, List<Show>> showList = new HashMap<>();

    public void addShow(String movieName,  List<Show> shows){
        showList.put(movieName, shows);
    }

    public List<Show> getAllShowsByMovieName(String movieName){
        List<Show> shows = showList.get(movieName);
        if (shows == null) {
            return Collections.emptyList();
        }
        return shows;
    }
    public Show getShowByMovieNameAndId(String movieName, int id){
        List<Show> shows = showList.get(movieName);
        if (shows == null) {
            return null;
        }
        for(Show show : shows){
            if(show.getShowId() == id){
                return show;
            }
        }
        return null;
    }
}

