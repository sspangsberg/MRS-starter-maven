package dk.easv.mrs.DAL;

// Java imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Project imports
import dk.easv.mrs.BE.Movie;

import static java.nio.file.StandardOpenOption.APPEND;


public class MovieDAO_File implements IMovieDataAccess {

    // relative path
    private static final String MOVIES_FILE = "data/movie_titles.txt";
    private Path filePath = Paths.get(MOVIES_FILE);

    //The @Override annotation is not required, but is recommended for readability
    // and to force the compiler to check and generate error msg. if needed etc.
    //@Override



    public List<Movie> getAllMovies() throws IOException {

        // Read all lines from file
        List<String> lines = Files.readAllLines(filePath);

        // Create list of movie objects
        List<Movie> movies = new ArrayList<>();

        // Parse each line as movie
        // Looop through all lines in the file (List)
        for (String line: lines) {

            // Map data to object

            String[] separatedLine = line.split(",");

            // individual movie items
            int id = Integer.parseInt(separatedLine[0]);
            int year = Integer.parseInt(separatedLine[1]);
            String title = separatedLine[2];

            if(separatedLine.length > 3)
            {
                for(int i = 3; i < separatedLine.length; i++)
                {
                    title += "," + separatedLine[i];
                }
            }

            // Create movie object
            Movie movie = new Movie(id, year, title);

            // Add to movies list
            movies.add(movie);
        }


        return movies;



    }


    @Override
    public Movie createMovie(Movie newMovie) throws Exception {

        List<String> movies = Files.readAllLines(filePath);

        if (movies.size() > 0) {
            // get next id
            String[] separatedLine = movies.get(movies.size() - 1).split(",");
            int nextId = Integer.parseInt(separatedLine[0]) + 1;
            String newMovieLine = nextId + "," + newMovie.getYear() + "," + newMovie.getTitle();
            Files.write(filePath, (newMovieLine + "\r\n").getBytes(), APPEND);

            return new Movie(nextId, newMovie.getYear(), newMovie.getTitle());
        }
        return null;
    }

    /**
     *
     * @param movie
     * @throws Exception
     */
    @Override
    public void updateMovie(Movie movie) throws Exception {
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
    }
}