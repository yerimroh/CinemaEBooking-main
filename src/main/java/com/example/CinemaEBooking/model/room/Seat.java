package com.example.CinemaEBooking.model.room;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; // id that is used to access the database
	private String seatNum; // seat representation that is viewed by the user

    private int statusId; // the foreign key to access the status
    private long roomId; // foreign key


	protected Seat() {}

	public Seat(int statusId, String seatNum, long roomId) {
        setStatus(statusId);
        setSeatNum(seatNum);
        setRoomId(roomId);
    } // Seat


	
    /* getters */
	public long getId() {
		return this.id;
	}

    public int getStatus() {
        return this.statusId;
    }

    public long getRoomId() {
        return this.roomId;
    }



    /* setters */
    public void setStatus(int statusId) {
        this.statusId = statusId;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }


    /* Methods */
    public boolean isAvailable() {
        return (this.statusId == 1);
    }


}
