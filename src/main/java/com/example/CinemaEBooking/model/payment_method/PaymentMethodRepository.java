package com.example.CinemaEBooking.model.payment_method;

import java.util.List;

import com.example.CinemaEBooking.model.customer.Customer;

import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {


    List<PaymentMethod> getPaymentMethodsByCustomer(Customer customer);

}