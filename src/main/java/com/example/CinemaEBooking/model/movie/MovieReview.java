package com.example.CinemaEBooking.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "reviews")
public class MovieReview {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id; // primary key

    private String author;
    private String content;
    private int rating; // out of 5
    private LocalDateTime datePosted;

    private long movieId; // foreign key to access movie info



    /* Constructors */
  protected MovieReview() {}

    public MovieReview(String author, String content, int rating, LocalDateTime datePosted, long movieId) {
        setAuthor(author);
        setContent(content);
        setRating(rating);
        setDatePosted(datePosted);
        setMovie(movieId);
    }

  /* getters */
    public long getMovieId() {
        return this.movieId;
    }

  public String getAuthor() {
      return this.author;
  }

  public String getContent() {
      return this.content;
  }

  public int getRating() {
    return this.rating;
  }

  public LocalDateTime getDatePosted() {
      return this.datePosted;
  }



  /* setters */

  public void setMovie(long movie){ 
      this.movieId = movie;
  }

  public void setAuthor(String author) {
      this.author = author;
  }

  public void setContent(String content) {
      this.content = content;
  }

  public void setRating(int rating) {
      this.rating = rating;
  }

  public void setDatePosted(LocalDateTime datePosted) {
      this.datePosted = datePosted;
  }


}
