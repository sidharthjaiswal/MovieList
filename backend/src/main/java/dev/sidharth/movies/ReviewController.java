package dev.sidharth.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Create a new review.
     *
     * @param payload A map containing "imdbId" and "reviewBody" for the review.
     * @return ResponseEntity with the created review and HTTP status.
     */
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        try {
            String imdbId = payload.get("imdbId");
            String reviewBody = payload.get("reviewBody");

            // Create a new Review object
            Review newReview = new Review(reviewBody, imdbId);

            // Call the createReview method from the service
            Review createdReview = reviewService.createReview(newReview, imdbId);

            // Return the createdReview in the response
            return new ResponseEntity<>(createdReview, HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions, log them, and return an appropriate response
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/history/{imdbId}")
    public ResponseEntity<List<Review>> getReviewHistory(@PathVariable String imdbId) {
        List<Review> reviewHistory = reviewService.getReviewHistory(imdbId);
        return new ResponseEntity<>(reviewHistory, HttpStatus.OK);
    }
}
