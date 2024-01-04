import React, { useEffect, useRef, useState } from 'react';
import api from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Table } from 'react-bootstrap';
import ReviewForm from './ReviewForm.js';

const Reviews = ({ getMovieData }) => {
  const revText = useRef();
  const params = useParams();
  const movieId = params.movieId;
  const [movie, setMovie] = useState(null);
  const [reviews, setReviews] = useState([]);

  const fetchReviewHistory = async () => {
    try {
      const response = await api.get(`/api/reviews/history/${movieId}`);
      const reviewHistory = response.data;
      setReviews(reviewHistory);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get(`/api/movies/${movieId}`);
        const singleMovie = response.data;
        setMovie(singleMovie);
        setReviews(singleMovie.reviews || []);

        // Fetch review history when component mounts
        await fetchReviewHistory(); // Use await to ensure it completes before moving on
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [movieId]);

  const addReview = async (e) => {
    e.preventDefault();
    const rev = revText.current;

    try {
      const response = await api.post("/api/reviews", { reviewBody: rev.value, imdbId: movieId });
      rev.value = "";

      // Fetch updated review history after adding a new review
      fetchReviewHistory();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <h3>Reviews</h3>
        </Col>
      </Row>
      {movie && (
        <Row className="mt-2">
          <Col>
            <img src={movie.poster} alt="" />
          </Col>
          <Col>
            <>
              <Row>
                <Col>
                  <ReviewForm handleSubmit={addReview} revText={revText} labelText="Write a Review?" />
                </Col>
              </Row>
              <Row>
                <Col>
                  <hr />
                </Col>
              </Row>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    <th>Review</th>
                    <th>Date</th>
                  </tr>
                </thead>
                <tbody>
                  {reviews.map((r, index) => (
                    <tr key={index}>
                      <td>{r.body}</td>
                      <td>{r.date}</td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </>
          </Col>
        </Row>
      )}
      <Row>
        <Col>
          <hr />
        </Col>
      </Row>
    </Container>
  );
};

export default Reviews;
