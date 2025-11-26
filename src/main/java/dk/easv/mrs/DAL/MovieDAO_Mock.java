package dk.easv.mrs.DAL;

// Project imports
import dk.easv.mrs.BE.Movie;

// Java imports
import java.util.ArrayList;
import java.util.List;


public class MovieDAO_Mock implements IRepository<Movie> {

    private List<Movie> allMovies;

    public MovieDAO_Mock()
    {
        allMovies = new ArrayList<>();
        allMovies.add(new Movie(1, 1991,"Terminator 2"));
        allMovies.add(new Movie(2, 2001,"Harry Potter and the Sorcerer´s Stone"));
        allMovies.add(new Movie(3, 2010, "Inception"));
        allMovies.add(new Movie(4, 1984, "Terminator"));
    }

    @Override
    public List<Movie> getAll() {
        return allMovies;
    }

    @Override
    public Movie create(Movie newMovie) throws Exception {
        return null;
    }

    @Override
    public void update(Movie movie) throws Exception {

    }

    @Override
    public void delete(Movie movie) throws Exception {

    }

}
