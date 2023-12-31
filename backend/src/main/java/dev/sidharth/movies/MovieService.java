package dev.sidharth.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /**
     * @return ll movies
     */
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    /**
     * @param imdbId
     * @return movie based on imdbId
     */
    public Optional<Movie> singleMovie(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }
}
