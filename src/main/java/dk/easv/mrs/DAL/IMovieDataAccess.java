package dk.easv.mrs.DAL;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.util.MRSException;

// Java imports
import java.util.List;

/**
 * Basic CRUD operations on the Movie
 */
public interface IMovieDataAccess {

    List<Movie> getAllMovies() throws MRSException;
    Movie createMovie(Movie newMovie) throws MRSException;
    void updateMovie(Movie movie) throws MRSException;
    void deleteMovie(Movie movie) throws MRSException;
}
