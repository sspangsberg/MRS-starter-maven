package dk.easv.mrs.DAL;

import dk.easv.mrs.BE.Rating;

public class DALFacade {

    private IMovieRepository movieDAO;
    private IRepository<Rating> ratingDAO;

    public IMovieRepository getMovieDAO() {
        return movieDAO;
    }

    public void setMovieDAO(IMovieRepository movieDAO) {
        this.movieDAO = movieDAO;
    }

    public IRepository<Rating> getRatingDAO() {
        return ratingDAO;
    }

    public void setRatingDAO(IRepository<Rating> ratingDAO) {
        this.ratingDAO = ratingDAO;
    }
}
