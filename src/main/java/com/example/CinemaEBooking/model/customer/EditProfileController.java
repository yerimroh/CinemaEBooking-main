package com.example.CinemaEBooking.model.customer;

import javax.validation.*;

import com.example.CinemaEBooking.model.address.Address;
import com.example.CinemaEBooking.model.address.AddressRepository;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.*;

import org.springframework.web.bind.*;

/**
 * This class represents the controller for the edit profile form. It handles
 * validation by setting its Validator to the EditProfile validator via 
 * initBinder.
 */
@Controller
public class EditProfileController implements WebMvcConfigurer {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressRepository addressRepository;



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/edit_profile").setViewName("editProfile");
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new EditProfileValidator());
    }

    @GetMapping("/user/edit_profile")
    public String showEditProfile(EditProfileForm editProfileForm) {
        Customer customer = customerService.getCurrentUser();

        editProfileForm.setFirstName(customer.getFirstName());
        editProfileForm.setLastName(customer.getLastName());
        editProfileForm.setPhoneNumber(customer.getPhoneNumber());
        editProfileForm.setSubscribed(customer.getSubscribed());

        if(customer.getAddress() != null) {
            editProfileForm.setStreetAddress(customer.getAddress().getStreetAddress());
            editProfileForm.setCity(customer.getAddress().getCity());
            editProfileForm.setState(customer.getAddress().getState());
            editProfileForm.setZipCode(customer.getAddress().getZipCode());
        } else {
            editProfileForm.setStreetAddress("");
            editProfileForm.setCity("");
            editProfileForm.setState("");
            editProfileForm.setZipCode("");
        } // if-else
    
        return "edit_profile";
    }

    @PostMapping("/user/edit_profile")
    public String checkEditProfile(@Valid EditProfileForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_profile";
        }
        
        // retrieve current user and update 
        Customer currentCustomer = customerService.getCurrentUser();
        currentCustomer.setFirstName(form.getFirstName());
        currentCustomer.setLastName(form.getLastName());
        currentCustomer.setPhoneNumber(form.getPhoneNumber());
        currentCustomer.setSubscribed(form.getSubscribed());

        if(currentCustomer.getAddress() == null && !form.getStreetAddress().isBlank()) {
            Address address = new Address(); // create a new address and add
            address.setStreetAddress(form.getStreetAddress());
            address.setCity(form.getCity());
            address.setState(form.getState());
            address.setZipCode(form.getZipCode());
            addressRepository.save(address);
            currentCustomer.setAddress(address);
        } else if(currentCustomer.getAddress() != null && !form.getStreetAddress().isBlank()) {
            Address address = customerService.getCurrentUser().getAddress(); // just update current adress
            address.setStreetAddress(form.getStreetAddress());
            address.setCity(form.getCity());
            address.setState(form.getState());
            address.setZipCode(form.getZipCode());
            addressRepository.save(address);
            currentCustomer.setAddress(address);
        } else if (currentCustomer.getAddress() != null && form.getStreetAddress().isBlank()) {
            addressRepository.deleteById(currentCustomer.getAddress().getId()); // if the user had a address but wants to delete the info by leaving the form blank
            currentCustomer.setAddress(null);
        }
        

        customerRepository.save(currentCustomer); // save customer to the db

        // Use "redirect:/[page name] so that it does not get just return the
        // page, but rather goes to the actual URL and maintains the logical
        // flow of events..
        return "redirect:/user/edit_profile";
    }
}
