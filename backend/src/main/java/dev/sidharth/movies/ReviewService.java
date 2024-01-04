package dev.sidharth.movies;// ReviewService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> allReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> singleReview(String imdbId) {
        return reviewRepository.findReviewByImdbId(imdbId);
    }

    public List<Review> getReviewsByMovieId(String imdbId) {
        return reviewRepository.findReviewsByImdbId(imdbId);
    }

    public Review createReview(Review review, String imdbId) {
        review.setImdbId(imdbId);
        return reviewRepository.save(review);
    }

    public List<Review> getReviewHistory(String imdbId) {
        return reviewRepository.findByImdbId(imdbId);
    }
}
