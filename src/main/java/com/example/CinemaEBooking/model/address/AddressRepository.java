package com.example.CinemaEBooking.model.address;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByState(String state);

    Address findById(long id);

    Address findByCustomerId(Long customerId);

    void deleteByCustomerId(Long id);
}
