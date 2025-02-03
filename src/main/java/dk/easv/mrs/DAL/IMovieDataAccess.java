package dk.easv.mrs.DAL;

// Project imports
import dk.easv.mrs.BE.Movie;

// Java imports
import java.util.List;

/**
 * Basic CRUD operations on the Movie
 */
public interface IMovieDataAccess {

    List<Movie> getAllMovies() throws Exception;
    Movie createMovie(Movie newMovie) throws Exception;
    void updateMovie(Movie movie) throws Exception;
    void deleteMovie(Movie movie) throws Exception;
}
