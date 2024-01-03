package dev.sidharth.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        String imdbId = payload.get("imdbId");
        String reviewBody = payload.get("reviewBody");

        // Create a new Review object
        Review newReview = new Review(reviewBody,imdbId);

        // Call the createReview method from the service
        Review createdReview = reviewService.createReview(newReview, imdbId);

        // Return the createdReview in the response
        return new ResponseEntity<>(createdReview, HttpStatus.OK);
    }
}
