package dev.sidharth.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private ObjectId id;
    private String imdbId;
    private Date date;
    private String body;

    /**
     * @param body
     * @param imdbId
     *
     */
    public Review(String body, String imdbId) {
        this.imdbId = imdbId;
        this.body = body;
        this.date = new Date();
    }
}
