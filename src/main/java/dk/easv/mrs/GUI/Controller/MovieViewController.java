package dk.easv.mrs.GUI.Controller;

// project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.GUI.MessageHandler;
import dk.easv.mrs.GUI.Model.MovieModel;
import dk.easv.mrs.util.MRSException;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {

    @FXML
    private TextField txtMovieSearch, txtTitle, txtYear;

    @FXML
    private Button btnClick;

    @FXML
    private TableView<Movie> tblMovies;

    @FXML
    private TableColumn<Movie, String> colTitle;

    @FXML
    private TableColumn<Movie, Integer> colYear;

    // Keep a reference to the model
    private MovieModel movieModel;

    /**
     * Constructor
     */
    public MovieViewController()  {
        try {
            movieModel = new MovieModel();
        } catch (MRSException e) {
            MessageHandler.displayErrorAlertBox(e);
        }
    }


    /**
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param resourceBundle
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // setup columns in tableview
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        // connect tableview to the ObservableList
        tblMovies.setItems(movieModel.getObservableMovies());

        // table view listener (when user selects a movie in the tableview)
        tblMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtTitle.setText(newValue.getTitle());
                txtYear.setText(Integer.toString(newValue.getYear()));

                btnClick.setText("Update");
            }
            else {
                txtTitle.setText("");
                txtYear.setText("");

                btnClick.setText("Create");
            }
        });

        // Listen to search input
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (MRSException err) {
                MessageHandler.displayErrorAlertBox(err);
            }
        });
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    private void onClick(ActionEvent actionEvent) {

        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            // Update existing movie
            updateMovie(selectedMovie);
        }

        else {
            // Create new movie
            createMovie();
        }
    }

    /**
     *
     */
    private void createMovie() {
        // get user movie data from UI
        String title = txtTitle.getText();
        int year = Integer.parseInt(txtYear.getText());

        // new movie object
        Movie newMovie = new Movie(-1, year, title);

        // Handle exception in the GUI layer
        try {
            // call model to create the movie in the dal
            movieModel.createMovie(newMovie);
        }
        catch (MRSException err) {
            MessageHandler.displayErrorAlertBox(err);
        }
    }


    /**
     *
     * @param selectedMovie
     */
    private void updateMovie(Movie selectedMovie) {

        // update movie based on textfield inputs from user
        selectedMovie.setTitle(txtTitle.getText());
        selectedMovie.setYear(Integer.parseInt(txtYear.getText()));

        // Handle exception in the GUI layer
        try {
            // Update movie in DAL layer (through the layers)
            movieModel.updateMovie(selectedMovie);
        } catch (MRSException err) {
            MessageHandler.displayErrorAlertBox(err);
        }
        // ask tableview to refresh their content
        tblMovies.refresh();
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    private void onDelete(ActionEvent actionEvent){
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null)
        {
            // Handle exception in the GUI layer
            try {
                // Delete movie in DAL layer (through the layers)
                movieModel.deleteMovie(selectedMovie);
            }
            catch (MRSException err) {
                MessageHandler.displayErrorAlertBox(err);
            }
        }
    }
}
