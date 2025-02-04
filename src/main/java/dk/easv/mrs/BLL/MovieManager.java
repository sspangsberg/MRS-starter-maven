package dk.easv.mrs.BLL;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BLL.util.MovieSearcher;
import dk.easv.mrs.DAL.IMovieDataAccess;
import dk.easv.mrs.DAL.db.MovieDAO_DB;
import dk.easv.mrs.util.MRSException;

// Java imports
import java.util.List;


public class MovieManager {

    private MovieSearcher movieSearcher = new MovieSearcher();
    private IMovieDataAccess movieDAO;

    public MovieManager() throws MRSException {
        movieDAO = new MovieDAO_DB(); // we need this coupling to DAL
    }

    /**
     * Get all movies from the DAL layer
     * @return
     * @throws Exception
     */
    public List<Movie> getAllMovies() throws MRSException {
        return movieDAO.getAllMovies();
    }


    /**
     * Search functionality
     * @param query
     * @return
     * @throws Exception
     */
    public List<Movie> searchMovies(String query) throws MRSException {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    /**
     * Create a new movie in the DAL layer
     * @param newMovie
     * @return
     * @throws Exception
     */
    public Movie createMovie(Movie newMovie) throws MRSException {
        return movieDAO.createMovie(newMovie);
    }



    /**
     * Update an existing movie in the DAL layer
     * @param updatedMovie
     * @throws Exception
     */
    public void updateMovie(Movie updatedMovie) throws MRSException {
        movieDAO.updateMovie(updatedMovie);
    }


    /**
     * Delete an existing movie based on its id
     * @param selectedMovie
     * @throws Exception
     */
    public void deleteMovie(Movie selectedMovie) throws MRSException {
        movieDAO.deleteMovie(selectedMovie);
    }
}
