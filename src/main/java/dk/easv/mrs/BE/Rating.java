package dk.easv.mrs.BE;

public class Rating {

    private int userId, movieId, score;

    public Rating(int userId, int movieId, int score) {
        this.userId = userId;
        this.movieId = movieId;
        this.score = score;
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
