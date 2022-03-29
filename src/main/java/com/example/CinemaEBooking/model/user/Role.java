package com.example.CinemaEBooking.model.user;


import javax.persistence.*;

public enum Role {

    ROLE_ADMIN("ADMIN"), ROLE_MEMBER("CUSTOMER");

    private String description;

    Role(String description) {
        this.description = description;
    }


    public String getDescription() {
        return this.description;
    }
}
