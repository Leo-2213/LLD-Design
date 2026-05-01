package LLDDesigns.BookMyShow.model;

import java.util.List;

public class Theater {
    private final String theaterName;
    private final List<Screen> screens;

    public String getTheaterName() {
        return theaterName;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public Theater(String name, List<Screen> screens) {
        this.theaterName = name;
        this.screens = screens;
    }
}
