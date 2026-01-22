package dk.easv.mrs.GUI;

// Project imports
import dk.easv.mrs.BLL.MovieManager;
import dk.easv.mrs.DAL.DALFacade;
import dk.easv.mrs.DAL.db.MovieDAO_DB;
import dk.easv.mrs.DAL.db.RatingDAO_DB;
import dk.easv.mrs.GUI.Controller.MovieViewController;
import dk.easv.mrs.GUI.Model.MRSModel;
import dk.easv.mrs.GUI.Model.MovieModel;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            // dependencies
            DALFacade mrsDalFacade = new DALFacade();
            mrsDalFacade.setMovieDAO(new MovieDAO_DB());
            //mrsDalFacade.setMovieDAO(new MovieDAO_Mock());
            //mrsDalFacade.setMovieDAO(new MovieDAO_File());
            mrsDalFacade.setRatingDAO(new RatingDAO_DB());

            MRSModel mrsModel = new MRSModel();
            MovieModel movieModel = new MovieModel(new MovieManager(mrsDalFacade.getMovieDAO()));
            mrsModel.setMovieModel(movieModel);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MovieView.fxml"));
            primaryStage.setTitle("MRS");
            primaryStage.setScene(new Scene(loader.load()));

            MovieViewController controller = loader.getController();
            controller.setModel(mrsModel);
            controller.setup();

            primaryStage.show();
        }
        catch (Exception err) { err.printStackTrace();}
    }

}
