package com.example.CinemaEBooking.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id; // primary key

    private String title;         
    private String casts;
    private String director;
    private String producer;
    private String synopsis;
    private String trailerURL;
    private String thumbnailURL;


    // foreign key to refer to the enums
    private long categoryId;
    private long ratingCodeId;


    /* Constructors */
  protected Movie() {}

  public Movie(String title, long categoryId, String casts, String director, String producer, 
  String synopsis, List<MovieReview> reviews, String trailerURL, String thumbnailURL, long ratingCodeId) {
    setTitle(title);
    setCategoryId(categoryId);
    setCast(casts);
    setDirector(director);
    setProducer(producer);
    setSynopsis(synopsis);
    setTrailerURL(trailerURL);
    setThumbnailURL(thumbnailURL);
    setRatingId(ratingCodeId);
  }


  /* getters */
  public String getTitle() {
      return this.title;
  }

  public long getCategoryId() {
      return this.categoryId;
  }

  public String getCasts() {
      return this.casts;
  }

  public String getDirector() {
      return this.director;
  }

  public String getProducer() {
      return this.producer;
  }

  public String getSynopsis() {
      return this.synopsis;
  }

  public String getThumbnailURL() {
      return this.thumbnailURL;
  }

  public String gettrailerURL() {
      return this.trailerURL;
  }

  public long getRatingCode() {
      return this.ratingCodeId;
  }

  /* setters */
  public void setTitle(String title) {
      this.title = title;
  }

  public void setRatingId(long ratingId) {
      this.ratingCodeId = ratingId;
  }

  public void setCategoryId(long categoryId) {
      this.categoryId = categoryId;
  }

  public void setDirector(String director) {
      this.director = director;
  }

  public void setCast(String casts) {
    this.casts = casts;
  }

  public void setProducer(String producer) {
      this.producer = producer;
  }

  public void setSynopsis(String synopsis) {
      this.synopsis = synopsis;
  }

  public void setTrailerURL(String trailerURL) {
      this.trailerURL = trailerURL;
  }

  public void setThumbnailURL(String thumbnailURL) {
      this.thumbnailURL = thumbnailURL;
  }



}
