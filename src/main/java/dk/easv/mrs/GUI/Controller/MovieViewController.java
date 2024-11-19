package dk.easv.mrs.GUI.Controller;

import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieViewController implements Initializable {


    public TextField txtMovieSearch;
    public ListView<Movie> lstMovies;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Movie> tblMovies;

    @FXML
    private TableColumn<Movie, String> colTitle;

    @FXML
    private TableColumn<Movie, Integer> colYear;

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
        // setup columns in tableview
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        // connect tableview + listview to the ObservableList
        tblMovies.setItems(movieModel.getObservableMovies());
        lstMovies.setItems(movieModel.getObservableMovies());

        // table view listener (when user selects a movie in the tableview)
        tblMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                txtTitle.setText(newValue.getTitle());
                txtYear.setText(Integer.toString(newValue.getYear()));

                btnUpdate.setDisable(false);
            }
            else {
                txtTitle.setText("");
                txtYear.setText("");

                btnUpdate.setDisable(true);
            }


        });

        // list view listener (when user selects a movie in the listview)
        lstMovies.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtTitle.setText(newValue.getTitle());
            txtYear.setText(Integer.toString(newValue.getYear()));
        });




        // Listen to search input
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                movieModel.searchMovie(newValue);
            } catch (Exception e) {
                displayError(e);
                e.printStackTrace();
            }
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
    private void onCreate(ActionEvent actionEvent) throws Exception {

        // get user movie data from UI
        String title = txtTitle.getText();
        int year = Integer.parseInt(txtYear.getText());

        // new movie object
        Movie newMovie = new Movie(-1, year, title);

        // call model to create the movie in the dal
        movieModel.createMovie(newMovie);
    }

    /**
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void onUpdate(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null) {
            // update movie based on textfield inputs from user
            selectedMovie.setTitle(txtTitle.getText());
            selectedMovie.setYear(Integer.parseInt(txtYear.getText()));

            // Update movie in DAL layer (through the layers)
            movieModel.updateMovie(selectedMovie);

            // ask controls to refresh their content
            lstMovies.refresh();
            tblMovies.refresh();
        }
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    private void onDelete(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null)
        {
            // Delete movie in DAL layer (through the layers)
            movieModel.deleteMovie(selectedMovie);

        }
    }
}
