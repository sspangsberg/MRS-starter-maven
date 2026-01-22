package dk.easv.mrs.DAL;

import dk.easv.mrs.BE.Movie;
import dk.easv.mrs.BE.Rating;

import java.util.List;

public interface IMovieRepository extends IRepository<Movie> {
    List<Rating> getRatingsForMovie(int movieId) throws Exception;
}
