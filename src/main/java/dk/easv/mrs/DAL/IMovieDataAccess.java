package dk.easv.mrs.DAL;

// Project imports
import dk.easv.mrs.BE.Movie;

// Java imports
import java.sql.SQLException;
import java.util.List;

/**
 * Basic CRUD operations on the Movie
 */
public interface IMovieDataAccess {

    List<Movie> getAllMovies() throws SQLException;
    Movie createMovie(Movie newMovie) throws SQLException;
    void updateMovie(Movie movie) throws SQLException;
    void deleteMovie(Movie movie) throws SQLException;
}
