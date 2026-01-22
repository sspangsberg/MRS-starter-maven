package dk.easv.mrs.GUI.Model;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BLL.MovieManager;

// Java imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;


public class MovieModel {

    private FilteredList<Movie> filteredList;
    private ObservableList<Movie> moviesToBeViewed;
    private MovieManager movieManager;

    /**
     *
     * @throws Exception
     */
    public MovieModel(MovieManager movieManager) throws Exception {
        this.movieManager = movieManager;
        moviesToBeViewed = FXCollections.observableArrayList();
        moviesToBeViewed.addAll(movieManager.getAllMovies());
        filteredList = new FilteredList<>(moviesToBeViewed);
    }


    /**
     *
     * @return
     */
    public FilteredList<Movie> getObservableMovies() {
        return filteredList;
    }

    /**
     *
     * @param newMovie
     * @return
     * @throws Exception
     */
    public Movie createMovie(Movie newMovie) throws Exception {
        Movie movieCreated = movieManager.createMovie(newMovie);
        moviesToBeViewed.add(movieCreated);
        return movieCreated;
    }

    /**
     *
     * @param updatedMovie
     * @throws Exception
     */
    public void updateMovie(Movie updatedMovie) throws Exception {
        // update movie in DAL layer (through the layers)
        movieManager.updateMovie(updatedMovie);

        // update observable list (and UI)
        Movie m = moviesToBeViewed.get(moviesToBeViewed.indexOf(updatedMovie));
        m.setTitle(updatedMovie.getTitle());
        m.setYear(updatedMovie.getYear());
    }


    /**
     *
     * @param selectedMovie
     * @throws Exception
     */
    public void deleteMovie(Movie selectedMovie) throws Exception {
        // delete movie in DAL layer (through the layers)
        movieManager.deleteMovie(selectedMovie);

        // remove from observable list (and UI)
        moviesToBeViewed.remove(selectedMovie);
    }
}
