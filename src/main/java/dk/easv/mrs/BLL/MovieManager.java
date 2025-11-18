package dk.easv.mrs.BLL;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.DAL.IMovieDataAccess;
import dk.easv.mrs.DAL.db.MovieDAO_DB;

// Java imports
import java.io.IOException;
import java.util.List;

public class MovieManager {

    private IMovieDataAccess movieDAO;

    public MovieManager() throws IOException {
        //movieDAO = new MovieDAO_Mock();
        //movieDAO = new MovieDAO_File();
        movieDAO = new MovieDAO_DB();
    }

    /**
     * Get all movies from the DAL layer
     * @return
     * @throws Exception
     */
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }


    /**
     * Create a new movie in the data source
     * @param newMovie
     * @return
     * @throws Exception
     */
    public Movie createMovie(Movie newMovie) throws Exception {
        return movieDAO.createMovie(newMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieDAO.deleteMovie(selectedMovie);
    }

    public void updateMovie(Movie updatedMovie) throws Exception {
        movieDAO.updateMovie(updatedMovie);
    }
}
