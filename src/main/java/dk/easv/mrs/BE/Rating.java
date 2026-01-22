package dk.easv.mrs.BE;

public class Rating {

    private int ratingId, userId, movieId, score;

    public Rating(int ratingId, int movieId, int userId, int score) {
        this.ratingId = ratingId;
        this.movieId = movieId;
        this.userId = userId;
        this.score = score;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", score=" + score +
                '}';
    }
}
