package com.example.CinemaEBooking.model.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String encryptedPassword;

	
	@Enumerated(EnumType.STRING)
	protected Role role; // either customer or admin
	
 

    public User() { }

    public User(String firstName, String lastName, String email,
            String encryptedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
    }

    public Long getId() {
		return this.id;
	}


	public String getEmail() {
		return this.email;
	}


	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Role getRole() {
		return this.role;
	}

    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	
	public boolean hasRole(String roleName) {
		return (this.role.getDescription().equals(roleName));
	}
	
}
