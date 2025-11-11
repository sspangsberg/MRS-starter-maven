package dk.easv.mrs.GUI.Controller;

import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;
    private MovieModel movieModel;

    @FXML
    private TextField txtTitle, txtYear;

    public MovieViewController()  {

        try {
            movieModel = new MovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        lstMovies.setItems(movieModel.getObservableMovies());

        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
        });


        // list view listener (when user selects a movie in the listview)
        lstMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtTitle.setText(newValue.getTitle());
            txtYear.setText(Integer.toString(newValue.getYear()));
        });
    }

    private void displayError(Throwable t)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(t.getMessage());
        alert.showAndWait();
    }

    @FXML
    private void btnHandleClick(ActionEvent actionEvent) throws Exception {

        // get user movie data from UI
        String title = txtTitle.getText();
        int year = Integer.parseInt(txtYear.getText());

        // new movie object
        Movie newMovie = new Movie(-1, year, title);

        // call model to create the movie in the dal
        movieModel.createMovie(newMovie);
    }

    @FXML
    private void btnHandleUpdate(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = lstMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {

            // update movie based on textfield inputs from user
            selectedMovie.setTitle(txtTitle.getText());
            selectedMovie.setYear(Integer.parseInt(txtYear.getText()));

            // Update movie in DAL layer (through the layers)
            movieModel.updateMovie(selectedMovie);

            // ask controls to refresh their content
            lstMovies.refresh();
        }
    }

    @FXML
    private void btnHandleDelete(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = lstMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null)
        {
            // Delete movie in DAL layer (through the layers)
            movieModel.deleteMovie(selectedMovie);
        }
    }
}
