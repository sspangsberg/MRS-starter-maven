package dk.easv.mrs.GUI.Model;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BLL.MovieManager;
import dk.easv.mrs.util.MRSException;

// Java imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class MovieModel {

    // The model keeps track of data
    private ObservableList<Movie> moviesToBeViewed;

    // and has a reference to the lower layer (BLL)
    private MovieManager movieManager;

    /**
     * Constructor
     * @throws MRSException
     */
    public MovieModel() throws MRSException {
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
    }

    /**
     * Getter for our observable list of movies
     * @return
     */
    public ObservableList<Movie> getObservableMovies() {
        return moviesToBeViewed;
    }

    /**
     * Search for a given movie based on a query
     * @param query
     * @throws MRSException
     */
    public void searchMovie(String query) throws MRSException {
        List<Movie> searchResults = movieManager.searchMovies(query);
        moviesToBeViewed.clear();
        moviesToBeViewed.addAll(searchResults);
    }

    /**
     * Create the new movie, by passing it to lower layer (BLL).
     * @param newMovie
     * @return
     * @throws MRSException
     */
    public Movie createMovie(Movie newMovie) throws MRSException {
        Movie movieCreated = movieManager.createMovie(newMovie);
        moviesToBeViewed.add(movieCreated);
        return movieCreated;
    }

    /**
     * Update the existing movie, by passing it to lower layer (BLL)
     * @param updatedMovie
     * @throws MRSException
     */
    public void updateMovie(Movie updatedMovie) throws MRSException {
        // update movie in DAL layer (through the layers)
        movieManager.updateMovie(updatedMovie);

        // update observable list (and UI)
        Movie m = moviesToBeViewed.get(moviesToBeViewed.indexOf(updatedMovie));

        m.setTitle(updatedMovie.getTitle());
        m.setYear(updatedMovie.getYear());
    }


    /**
     * Delete the existing movie, by passing it to lower layer (BLL)
     */
    public void deleteMovie(Movie selectedMovie) throws MRSException {
        // delete movie in DAL layer (through the layers)
        movieManager.deleteMovie(selectedMovie);

        // remove from observable list (and UI)
        moviesToBeViewed.remove(selectedMovie);
    }
}
