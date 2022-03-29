package com.example.CinemaEBooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

import com.example.CinemaEBooking.model.*;
import com.example.CinemaEBooking.model.address.AddressRepository;
import com.example.CinemaEBooking.model.customer.CustomerRepository;

/* 
 * These four imports are for creating and running a demo. Delete when not 
 * needed.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ComponentScan({"com.example.CinemaEBooking.model"})
public class CinemaEBookingApplication {

    private static final Logger log =
        LoggerFactory.getLogger(CinemaEBookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CinemaEBookingApplication.class, args);
	}


    @Bean 
    public CommandLineRunner demo(CustomerRepository customerRepository,
            AddressRepository addressRepository) {

        return args -> {
            log.info("Customers found with findAll():");
            customerRepository.findAll().forEach(e -> {
                log.info(e.toString());
            });

        };
    }
}
