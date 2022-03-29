package com.example.CinemaEBooking.model.movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class MovieCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id; 
    // 1: Romance, 2: Action, 3: Drama, 4: Comedy, 5: Fantasy, 6:Thriller, 7: Sci-Fi
    
    
    private String category;



	protected MovieCategory() {}

	public MovieCategory(String category) {
        setCategory(category);
    } // MovieCategory


	
    /* getters */
	public String getCategory() {
		return this.category;
	}


    /* setters */
    public void setCategory(String category) {
        this.category = category;
    }


}
