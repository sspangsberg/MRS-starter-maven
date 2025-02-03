package dk.easv.mrs.BE;

public class Movie {

    private int id;
    private String title;
    private int year;


    /**
     *
     * @param id
     * @param year
     * @param title
     */
    public Movie(int id, int year, String title) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return id + ": " + title + " ("+year+")";
    }

}
