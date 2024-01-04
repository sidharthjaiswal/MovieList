package dev.sidharth.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    Optional<Review> findReviewByImdbId(String imdbId);
    List<Review> findReviewsByImdbId(String imdbId);
    List<Review> findByImdbId(String imdbId);
}
