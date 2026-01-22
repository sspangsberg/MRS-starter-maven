package dk.easv.mrs.GUI.Controller;

// Project imports
import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.GUI.Model.MRSModel;
import dk.easv.mrs.GUI.Model.MovieModel;

// Java imports
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class MovieViewController implements BaseController {

    @FXML private Button btnUpdate;
    @FXML private TableView<Movie> tblMovies;
    @FXML private TableColumn<Movie, String> colTitle;
    @FXML private TableColumn<Movie, Integer> colYear;
    @FXML private TextField txtMovieSearch, txtTitle, txtYear;

    private MRSModel mrsModel;
    private MovieModel movieModel;

    /**
     *
     * @param mrsModel
     */
    public void setModel(MRSModel mrsModel) {
        try {
            this.mrsModel = mrsModel;
            this.movieModel = mrsModel.getMovieModel();
        } catch (Exception e) {
            displayError(e);
            e.printStackTrace();
        }
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
            //lstMovies.refresh();
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


    @Override
    public void setup() {

        // setup columns in tableview
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        // connect tableview to the ObservableList (FilteredList)
        tblMovies.setItems(movieModel.getObservableMovies());

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

        // Listen to search input
        txtMovieSearch.textProperty().addListener((observableValue, oldValue, newValue) ->
        {

            movieModel.getObservableMovies().setPredicate(movie -> {

                // If filter text is empty, display all movies.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (movie.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return Integer.toString(movie.getYear()).contains(lowerCaseFilter);
            });
        });

        SortedList<Movie> sortedData = new SortedList<>(movieModel.getObservableMovies());
        sortedData.comparatorProperty().bind(tblMovies.comparatorProperty());
        tblMovies.setItems(sortedData);
    }

}
