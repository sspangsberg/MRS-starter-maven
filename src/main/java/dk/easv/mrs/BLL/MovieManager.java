package dk.easv.mrs.BLL;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.DAL.IRepository;
import dk.easv.mrs.DAL.db.MovieDAO_DB;

// Java imports
import java.io.IOException;
import java.util.List;

public class MovieManager {

    private IRepository<Movie> movieDAO;

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
        return movieDAO.getAll();
    }

    /**
     * Create a new movie in the data source
     * @param newMovie
     * @return
     * @throws Exception
     */
    public Movie createMovie(Movie newMovie) throws Exception {
        return movieDAO.create(newMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieDAO.delete(selectedMovie);
    }

    public void updateMovie(Movie updatedMovie) throws Exception {
        movieDAO.update(updatedMovie);
    }
}
