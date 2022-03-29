package com.example.CinemaEBooking.model.room;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; // primary key
    private String roomNum; // room number that is reflected on the ticket


	protected Room() {}

	public Room(String roomNum, List<Seat> seats) {
        setRoomNum(roomNum);
    } // Room


	
    /* getters */
	public String getRoomNum() {
		return this.roomNum;
	}



    /* setters */
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

}
