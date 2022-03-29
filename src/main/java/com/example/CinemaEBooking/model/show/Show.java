package com.example.CinemaEBooking.model.show;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

/*
    Many Shows are associated with One Movie
    One Show can have many Bookings
    Many shows are associated with One Room
*/
@Entity
@Table(name = "shows")
public class Show {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id; // primary key

  private int duration; // in minutes
  private LocalDateTime showTime;

  private long movieId; // foreign key to access the movie info
  private long roomId; // foreign key to access the room info



    /* Constructors */
  protected Show() {}

  public Show(int duration, LocalDateTime showTime, long movieId, long roomId) {
      setDuration(duration);
      setShowTime(showTime);
      setMovie(movieId);
      setRoom(roomId);
  }

  /* getters */
  public int getDuration() {
      return this.duration;
  }


  public LocalDateTime getShowTime() {
      return this.showTime;
  }


  public long getMovieId() {
      return this.movieId;
  }


  public long getRoomId() {
      return this.roomId;
  }


  /* setters */
  public void setDuration(int duration) {
    this.duration = duration;
  }


  public void setShowTime(LocalDateTime showTime) {
      this.showTime = showTime;
  }

  public void setMovie(long movieId) {
      this.movieId = movieId;
  }

  public void setRoom(long roomID) {
      this.roomId = roomID;
  }


}
