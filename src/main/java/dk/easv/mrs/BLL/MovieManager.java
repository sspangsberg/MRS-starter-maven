package dk.easv.mrs.BLL;
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BLL.util.MovieSearcher;
import dk.easv.mrs.DAL.IMovieDataAccess;
import dk.easv.mrs.DAL.MovieDAO_File;
import dk.easv.mrs.DAL.MovieDAO_Mock;
import java.util.List;

public class MovieManager {

    private MovieSearcher movieSearcher = new MovieSearcher();
    private IMovieDataAccess movieDAO;

    public MovieManager() {
        //movieDAO = new MovieDAO_Mock();
        movieDAO = new MovieDAO_File();
        //movieDAO = new MovieDAO_DB();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }

    public Movie createMovie(Movie newMovie) throws Exception {
        return movieDAO.createMovie(newMovie);
    }

    public void updateMovie(Movie updatedMovie) throws Exception {
        movieDAO.updateMovie(updatedMovie);
    }

    public void deleteMovie(Movie movieToBeDeleted) throws Exception {
        movieDAO.deleteMovie(movieToBeDeleted);
    }

}
